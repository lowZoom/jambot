package luj.game.robot.internal.session.inject;


import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.game.robot.api.action.RobotDataCommand;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.plugin.JambotBootInit;
import luj.game.robot.api.plugin.JambotDynamicInit;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;
import org.springframework.beans.factory.annotation.Autowired;

final class InjectRoot implements StaticBeanCollector.Result {

  @Override
  public List<RobotStartListener> getStartListeners() {
    return _startListeners;
  }

  @Override
  public List<RobotProtoHandler<?>> getProtoHandlerList() {
    return _protoHandlerList;
  }

  @Override
  public RobotInstanceInjectRoot getInstanceInjectRoot() {
    return _instanceInjectRoot;
  }

  @Override
  public List<RobotDataCommand<?>> getCommandList() {
    return _commandList;
  }

  @Override
  public JambotBootInit getBootInitPlugin() {
    return _bootInitPlugin;
  }

  @Override
  public JambotDynamicInit getDynamicInitPlugin() {
    return _dynamicInitPlugin;
  }

  @Autowired(required = false)
  List<RobotStartListener> _startListeners = ImmutableList.of();

  @Autowired(required = false)
  List<RobotProtoHandler<?>> _protoHandlerList = ImmutableList.of();

  @Autowired
  RobotInstanceInjectRoot _instanceInjectRoot;

  @Autowired(required = false)
  List<RobotDataCommand<?>> _commandList = ImmutableList.of();

  @Autowired
  JambotBootInit _bootInitPlugin;

  @Autowired(required = false)
  JambotDynamicInit _dynamicInitPlugin;
}
