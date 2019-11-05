package luj.game.robot.internal.start;

import luj.game.robot.internal.session.inject.RobotBeanCollector;
import luj.net.api.NetContext;

public class BotbeanInLujcluster {

  public BotbeanInLujcluster(RobotBeanCollector.Result injectRoot, NetContext lujnet) {
    _injectRoot = injectRoot;
    _lujnet = lujnet;
  }

  public RobotBeanCollector.Result getInjectRoot() {
    return _injectRoot;
  }

  public NetContext getLujnet() {
    return _lujnet;
  }

  private final RobotBeanCollector.Result _injectRoot;

  private final NetContext _lujnet;
}
