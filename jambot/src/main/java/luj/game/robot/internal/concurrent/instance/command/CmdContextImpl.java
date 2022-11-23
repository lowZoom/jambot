package luj.game.robot.internal.concurrent.instance.command;

import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.internal.start.botinstance.RobotState;
import org.slf4j.Logger;

final class CmdContextImpl implements RobotCommand.Context {

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
  public Logger getLogger() {
    return _logger;
  }

  @Override
  public void executeCommand(Class<? extends RobotCommand<?>> cmdType) {
    new CommandExecuteStarterV2(_instanceRef, cmdType, _cmdType, _param).start();
  }

  @Override
  public RobotCommand.Service service() {
    return _service;
  }

  RobotState _botState;
  int _botIndex;

  ActorMessageHandler.Ref _instanceRef;
  RobotCommand.Service _service;

  String _cmdType;
  Object _param;

  Logger _logger;
}
