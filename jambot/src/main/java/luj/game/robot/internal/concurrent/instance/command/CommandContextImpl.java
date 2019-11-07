package luj.game.robot.internal.concurrent.instance.command;

import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.internal.net.send.BotProtoSender;
import luj.game.robot.internal.start.botinstance.RobotState;

final class CommandContextImpl implements RobotCommand.Context {

  CommandContextImpl(RobotState robotState,
      RobotProtoEncoder protoEncoder) {
    _robotState = robotState;
    _protoEncoder = protoEncoder;
  }

  @Override
  public void send(Object proto) {
    new BotProtoSender(proto, _protoEncoder, _robotState.getConnection()).send();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <D> D getData(Class<D> dataType) {
    return (D) _robotState.getDataMap().get(dataType);
  }

  private final RobotState _robotState;

  private final RobotProtoEncoder _protoEncoder;
}
