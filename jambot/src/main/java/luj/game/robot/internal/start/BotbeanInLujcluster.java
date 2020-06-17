package luj.game.robot.internal.start;

import luj.bean.api.BeanContext;
import luj.game.robot.internal.session.inject.RobotBeanCollector;
import luj.net.api.NetContext;

public class BotbeanInLujcluster {

  public BotbeanInLujcluster(RobotBeanCollector.Result injectRoot, NetContext lujnet,
      BeanContext lujbean) {
    _injectRoot = injectRoot;
    _lujnet = lujnet;
    _lujbean = lujbean;
  }

  public RobotBeanCollector.Result getInjectRoot() {
    return _injectRoot;
  }

  public NetContext getLujnet() {
    return _lujnet;
  }

  public BeanContext getLujbean() {
    return _lujbean;
  }

  private final RobotBeanCollector.Result _injectRoot;

  private final NetContext _lujnet;
  private final BeanContext _lujbean;
}
