package luj.game.robot.internal.concurrent.instance.command;

import java.util.Map;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.api.action.CommandService;
import luj.game.robot.api.action.RobotDataCommand;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.concurrent.instance.command.service.factory.CommandServiceFactory;
import luj.game.robot.internal.start.botinstance.RobotState;

final class CmdRobotImpl implements RobotDataCommand.Robot {

  @Override
  public void putData(Object data) {
    _botState.getDataMap().put(data.getClass(), data);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <D> D getData(Class<D> dataType) {
    return (D) _botState.getDataMap().get(dataType);
  }

  @Override
  public <P> CommandService<P> command(Class<? extends RobotDataCommand<P>> cmdType) {
    return new CommandServiceFactory(_instanceRef, _param, _cmdType,
        _dependency.getLujbean(), _dependency.getCommandMap(), cmdType).create();
  }

  RobotState _botState;

  ActorMessageHandler.Ref _instanceRef;
  RobotInstanceDependency _dependency;

  String _cmdType;
  Map<String, Object> _param;
}
