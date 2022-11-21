package luj.game.robot.internal.session;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.bean.api.BeanContext;
import luj.bean.api.LujBean;
import luj.cluster.api.LujCluster;
import luj.cluster.api.node.ClusterNode;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.internal.dynamic.init.DynamicInitInvoker;
import luj.game.robot.internal.session.inject.RobotBeanCollector;
import luj.game.robot.internal.sessionv2.plugin.start.BootInitInvoker;
import luj.game.robot.internal.start.BotbeanInLujcluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RobotSessionStarterV2 {

  public RobotSessionStarterV2(ApplicationContext appContext) {
    _appContext = appContext;
  }

  public void start() {
    RobotBeanCollector.Result staticRoot = new RobotBeanCollector(_appContext).collect();
    BootInitInvoker.Result bootCfg = new BootInitInvoker(staticRoot.getBootInitPlugin()).invoke();

    DynamicInitInvoker.Result dynamicRoot = new DynamicInitInvoker(
        staticRoot.getDynamicInitPlugin(), bootCfg.startParam()).invoke();

    List<RobotStartListener> listenerList = ImmutableList.<RobotStartListener>builder()
        .addAll(staticRoot.getStartListeners())
        .addAll(dynamicRoot.getStartListeners())
        .build();

    if (listenerList.isEmpty()) {
      LOG.warn("没有启动逻辑，机器人结束：{}", RobotStartListener.class.getName());
      return;
    }

    try (AnnotationConfigApplicationContext internalCtx = new AnnotationConfigApplicationContext()) {
      internalCtx.register(InjectConf.class);
      internalCtx.refresh();

      startLujcluster(internalCtx, staticRoot, listenerList);
    }
  }

  /**
   * @see luj.game.robot.internal.start.OnLujclusterStart
   */
  private ClusterNode startLujcluster(ApplicationContext botCtx,
      RobotBeanCollector.Result injectRoot, List<RobotStartListener> startList) {
    BeanContext lujbean = LujBean.start();
    BotbeanInLujcluster botbean = new BotbeanInLujcluster(injectRoot, startList, lujbean);

//    List<String> seeds = ImmutableList.of(_host + ":" + _port);
    return LujCluster.start(botCtx).startNode(c -> c
        .startParam(botbean)
    );
  }

  private static final Logger LOG = LoggerFactory.getLogger(RobotSessionStarterV2.class);

  private final ApplicationContext _appContext;
}
