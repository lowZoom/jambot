package luj.game.robot.internal.concurrent.instance.command;

import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.internal.net.send.BotProtoSender;
import luj.game.robot.internal.start.botinstance.RobotState;

final class CmdContextImpl implements RobotCommand.Context {

  CmdContextImpl(RobotState robotState, RobotProtoEncoder protoEncoder,
      ActorMessageHandler.Ref instanceRef, RobotCommand.Service service) {
    _robotState = robotState;
    _protoEncoder = protoEncoder;
    _instanceRef = instanceRef;
    _service = service;
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

  @Override
  public RobotCommand.Service service() {
    return _service;
  }

  private final RobotState _robotState;

  private final RobotProtoEncoder _protoEncoder;
  private final ActorMessageHandler.Ref _instanceRef;

  private final RobotCommand.Service _service;
}
