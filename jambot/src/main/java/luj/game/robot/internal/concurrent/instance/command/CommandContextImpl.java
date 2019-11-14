package luj.game.robot.internal.concurrent.instance.command;

import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.internal.net.send.BotProtoSender;
import luj.game.robot.internal.start.botinstance.RobotState;

final class CommandContextImpl implements RobotCommand.Context {

  CommandContextImpl(RobotState robotState, RobotProtoEncoder protoEncoder,
      ActorMessageHandler.Ref instanceRef) {
    _robotState = robotState;
    _protoEncoder = protoEncoder;
    _instanceRef = instanceRef;
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

  @Override
  public void executeCommand(Class<? extends RobotCommand> cmdType) {
    new CommandExecuteStarter(_instanceRef, cmdType).start();
  }

  private final RobotState _robotState;

  private final RobotProtoEncoder _protoEncoder;
  private final ActorMessageHandler.Ref _instanceRef;
}
