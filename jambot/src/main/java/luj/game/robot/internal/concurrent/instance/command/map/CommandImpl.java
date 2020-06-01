package luj.game.robot.internal.concurrent.instance.command.map;

import luj.game.robot.api.action.RobotCommand;

final class CommandImpl implements CommandMap.Command {

  @Override
  public RobotCommand<?> getInstance() {
    return _command;
  }

  @Override
  public Class<?> getCommandType() {
    return _commandType;
  }

  @Override
  public Class<?> getParamType() {
    return _paramType;
  }

  RobotCommand<?> _command;

  Class<?> _commandType;
  Class<?> _paramType;
}
