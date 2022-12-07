package luj.game.robot.internal.concurrent.instance;

import java.util.List;
import java.util.Map;
import luj.bean.api.BeanContext;
import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;

public class RobotInstanceDependency {

  public RobotInstanceDependency(RobotInstanceInjectRoot injectRoot, BeanContext lujbean,
      List<RobotCreateListener> createList, CommandMap commandMap,
      Map<Class<?>, RobotProtoHandler<?>> protoHandleMap) {
    _injectRoot = injectRoot;
    _lujbean = lujbean;
    _createList = createList;
    _commandMap = commandMap;
    _protoHandleMap = protoHandleMap;
  }

  public RobotInstanceInjectRoot getInjectRoot() {
    return _injectRoot;
  }

  public BeanContext getLujbean() {
    return _lujbean;
  }

  public List<RobotCreateListener> getCreateList() {
    return _createList;
  }

  public CommandMap getCommandMap() {
    return _commandMap;
  }

  public Map<Class<?>, RobotProtoHandler<?>> getProtoHandleMap() {
    return _protoHandleMap;
  }

  private final RobotInstanceInjectRoot _injectRoot;
  private final BeanContext _lujbean;

  private final List<RobotCreateListener> _createList;
  private final CommandMap _commandMap;

  private final Map<Class<?>, RobotProtoHandler<?>> _protoHandleMap;
}
