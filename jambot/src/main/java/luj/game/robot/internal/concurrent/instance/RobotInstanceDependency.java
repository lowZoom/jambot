package luj.game.robot.internal.concurrent.instance;

import java.util.Map;
import luj.bean.api.BeanContext;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;

public class RobotInstanceDependency {

  public RobotInstanceDependency(RobotInstanceInjectRoot injectRoot, BeanContext lujbean,
      Map<Class<?>, RobotProtoHandler<?>> protoHandleMap, CommandMap commandMap) {
    _injectRoot = injectRoot;
    _lujbean = lujbean;
    _protoHandleMap = protoHandleMap;
    _commandMap = commandMap;
  }

  public RobotInstanceInjectRoot getInjectRoot() {
    return _injectRoot;
  }

  public BeanContext getLujbean() {
    return _lujbean;
  }

  public Map<Class<?>, RobotProtoHandler<?>> getProtoHandleMap() {
    return _protoHandleMap;
  }

  public CommandMap getCommandMap() {
    return _commandMap;
  }

  private final RobotInstanceInjectRoot _injectRoot;
  private final BeanContext _lujbean;

  private final Map<Class<?>, RobotProtoHandler<?>> _protoHandleMap;
  private final CommandMap _commandMap;
}
