package luj.game.robot.internal.session.inject;


import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.ava.spring.Internal;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.plugin.JambotBootInit;
import luj.game.robot.api.plugin.JambotDynamicInit;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class InjectRoot implements RobotBeanCollector.Result {

  @Override
  public List<RobotStartListener> getStartListeners() {
    return _startListeners;
  }

  @Override
  public RobotProtoEncoder getProtoEncoder() {
    return _protoEncoder;
  }

  @Override
  public RobotProtoDecoder getProtoDecoder() {
    return _protoDecoder;
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
  public List<RobotCommand<?>> getCommandList() {
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

  @Autowired
  RobotProtoEncoder _protoEncoder;

  @Autowired
  RobotProtoDecoder _protoDecoder;

  @Autowired(required = false)
  List<RobotProtoHandler<?>> _protoHandlerList = ImmutableList.of();

  @Autowired
  RobotInstanceInjectRoot _instanceInjectRoot;

  @Autowired(required = false)
  List<RobotCommand<?>> _commandList = ImmutableList.of();

  @Autowired
  JambotBootInit _bootInitPlugin;

  @Autowired(required = false)
  JambotDynamicInit _dynamicInitPlugin;
}
