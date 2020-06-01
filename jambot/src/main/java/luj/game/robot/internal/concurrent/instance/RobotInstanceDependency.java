package luj.game.robot.internal.concurrent.instance;

import java.util.Map;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;
import luj.net.api.NetContext;

public class RobotInstanceDependency {

  public RobotInstanceDependency(RobotInstanceInjectRoot injectRoot, NetContext lujnet,
      Map<Class<?>, RobotProtoHandler<?>> protoHandleMap, CommandMap commandMap) {
    _injectRoot = injectRoot;
    _lujnet = lujnet;
    _protoHandleMap = protoHandleMap;
    _commandMap = commandMap;
  }

  public RobotInstanceInjectRoot getInjectRoot() {
    return _injectRoot;
  }

  public NetContext getLujnet() {
    return _lujnet;
  }

  public Map<Class<?>, RobotProtoHandler<?>> getProtoHandleMap() {
    return _protoHandleMap;
  }

  public CommandMap getCommandMap() {
    return _commandMap;
  }

  private final RobotInstanceInjectRoot _injectRoot;
  private final NetContext _lujnet;

  private final Map<Class<?>, RobotProtoHandler<?>> _protoHandleMap;
  private final CommandMap _commandMap;
}
