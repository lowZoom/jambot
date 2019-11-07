package luj.game.robot.internal.session.inject;


import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.ava.spring.Internal;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class InjectRoot implements RobotBeanCollector.Result {

  @Override
  public List<RobotStartListener> getStartListeners() {
    return nonNull(_startListeners);
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
    return nonNull(_protoHandlerList);
  }

  @Override
  public RobotInstanceInjectRoot getInstanceInjectRoot() {
    return _instanceInjectRoot;
  }

  @Override
  public List<RobotCommand> getCommandList() {
    return nonNull(_commandList);
  }

  private <T> List<T> nonNull(List<T> list) {
    return MoreObjects.firstNonNull(list, ImmutableList.of());
  }

  @Autowired(required = false)
  private List<RobotStartListener> _startListeners;

  @Autowired
  private RobotProtoEncoder _protoEncoder;

  @Autowired
  private RobotProtoDecoder _protoDecoder;

  @Autowired(required = false)
  private List<RobotProtoHandler<?>> _protoHandlerList;

  @Autowired
  private RobotInstanceInjectRoot _instanceInjectRoot;

  @Autowired(required = false)
  private List<RobotCommand> _commandList;
}
