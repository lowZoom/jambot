package luj.game.robot.internal.concurrent.instance.command;

import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.internal.start.botinstance.RobotState;

final class CmdContextImpl implements RobotCommand.Context {

  CmdContextImpl(RobotState botState, int botIndex,
      ActorMessageHandler.Ref instanceRef, RobotCommand.Service service, Object param) {
    _botState = botState;
    _botIndex = botIndex;
    _instanceRef = instanceRef;
    _service = service;
    _param = param;
  }

  @Override
  public void send(Object proto) {
    _service.network().send(proto);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getParam(RobotCommand<T> command) {
    return (T) _param;
  }

  @Override
  public int getRobotIndex() {
    return _botIndex;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <D> D getData(Class<D> dataType) {
    return (D) _botState.getDataMap().get(dataType);
  }

  @Override
  public void executeCommand(Class<? extends RobotCommand> cmdType) {
    new CommandExecuteStarter(_instanceRef, cmdType).start();
  }

  @Override
  public RobotCommand.Service service() {
    return _service;
  }

  private final RobotState _botState;
  private final int _botIndex;

  private final ActorMessageHandler.Ref _instanceRef;
  private final RobotCommand.Service _service;

  private final Object _param;
}
