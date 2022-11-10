package luj.game.robot.internal.start;

import java.util.List;
import luj.bean.api.BeanContext;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.internal.session.inject.RobotBeanCollector;
import luj.net.api.NetContext;

public class BotbeanInLujcluster {

  public BotbeanInLujcluster(RobotBeanCollector.Result injectRoot,
      List<RobotStartListener> startListeners, NetContext lujnet, BeanContext lujbean) {
    _injectRoot = injectRoot;
    _startListeners = startListeners;
    _lujnet = lujnet;
    _lujbean = lujbean;
  }

  public RobotBeanCollector.Result getInjectRoot() {
    return _injectRoot;
  }

  public List<RobotStartListener> getStartListeners() {
    return _startListeners;
  }

  public NetContext getLujnet() {
    return _lujnet;
  }

  public BeanContext getLujbean() {
    return _lujbean;
  }

  private final RobotBeanCollector.Result _injectRoot;
  private final List<RobotStartListener> _startListeners;

  private final NetContext _lujnet;
  private final BeanContext _lujbean;
}
