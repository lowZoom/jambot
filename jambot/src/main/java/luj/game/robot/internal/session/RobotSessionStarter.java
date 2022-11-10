package luj.game.robot.internal.session;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.bean.api.BeanContext;
import luj.bean.api.LujBean;
import luj.cluster.api.LujCluster;
import luj.cluster.api.node.ClusterNode;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.internal.session.inject.RobotBeanCollector;
import luj.game.robot.internal.start.BotbeanInLujcluster;
import luj.net.api.LujNet;
import luj.net.api.NetContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @see RobotSessionStarterV2
 */
@Deprecated
public class RobotSessionStarter {

  public RobotSessionStarter(String host, int port, ApplicationContext appContext) {
    _host = host;
    _port = port;
    _appContext = appContext;
  }

  public ClusterNode start() {
    RobotBeanCollector.Result root = new RobotBeanCollector(_appContext).collect();

    List<RobotStartListener> listenerList = root.getStartListeners();
    if (listenerList.isEmpty()) {
      LOG.warn("没有启动逻辑，机器人结束：{}", RobotStartListener.class.getName());
      return null;
    }

    return startLujcluster(_appContext, root);
  }

  /**
   * @see luj.game.robot.internal.start.OnLujclusterStart
   */
  private ClusterNode startLujcluster(ApplicationContext botCtx,
      RobotBeanCollector.Result injectRoot) {
    NetContext lujnet = LujNet.create(botCtx);
    BeanContext lujbean = LujBean.start();

    BotbeanInLujcluster botbean = new BotbeanInLujcluster(
        injectRoot, injectRoot.getStartListeners(), lujnet, lujbean);

    List<String> seeds = ImmutableList.of(_host + ":" + _port);
    return LujCluster.start(botCtx).startNode(_host, _port, seeds, botbean);
  }

  private static final Logger LOG = LoggerFactory.getLogger(RobotSessionStarter.class);

  private final String _host;
  private final int _port;

  private final ApplicationContext _appContext;
}
