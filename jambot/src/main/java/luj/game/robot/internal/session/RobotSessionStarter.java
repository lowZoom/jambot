package luj.game.robot.internal.session;

import java.util.List;
import luj.cluster.api.LujCluster;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.internal.session.inject.RobotBeanCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class RobotSessionStarter {

  public RobotSessionStarter(String host, int port, ApplicationContext appContext) {
    _host = host;
    _port = port;
    _appContext = appContext;
  }

  public void start() {
    RobotBeanCollector.Result root = new RobotBeanCollector(_appContext).collect();
    List<RobotStartListener> listenerList = root.getStartListeners();

    if (listenerList.isEmpty()) {
      LOG.warn("没有启动逻辑，机器人结束：{}", RobotStartListener.class.getName());
      return;
    }

    LujCluster.start(_appContext).startNode(_host, _port, _host + ":" + _port, null);
  }

  private static final Logger LOG = LoggerFactory.getLogger(RobotSessionStarter.class);

  private final String _host;
  private final int _port;

  private final ApplicationContext _appContext;
}
