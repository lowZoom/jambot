package luj.game.robot.internal.concurrent.instance;

import java.util.Map;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;
import luj.net.api.NetContext;

public class RobotInstanceDependency {

  public RobotInstanceDependency(RobotInstanceInjectRoot injectRoot,
      NetContext lujnet, Map<Class<?>, RobotCommand> commandMap) {
    _injectRoot = injectRoot;
    _lujnet = lujnet;
    _commandMap = commandMap;
  }

  public RobotInstanceInjectRoot getInjectRoot() {
    return _injectRoot;
  }

  public NetContext getLujnet() {
    return _lujnet;
  }

  public Map<Class<?>, RobotCommand> getCommandMap() {
    return _commandMap;
  }

  private final RobotInstanceInjectRoot _injectRoot;

  private final NetContext _lujnet;
  private final Map<Class<?>, RobotCommand> _commandMap;
}
