package luj.game.robot.internal.concurrent.instance.command.map;

import luj.game.robot.api.action.RobotDataCommand;
import org.slf4j.Logger;

final class CommandImpl implements CommandMap.Command {

  @Override
  public RobotDataCommand<?> getInstance() {
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

  @Override
  public Logger getLogger() {
    return _logger;
  }

  String getCommandName() {
    return _commandType.getName();
  }

  RobotDataCommand<?> _command;

  Class<?> _commandType;
  Class<?> _paramType;

  Logger _logger;
}
