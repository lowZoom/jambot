package luj.game.robot.internal.concurrent.instance.command;

import luj.game.robot.api.action.RobotDataCommand;
import org.slf4j.Logger;

final class CmdContextImpl implements RobotDataCommand.Context {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T param(RobotDataCommand<T> command) {
    return (T) _param;
  }

  @Override
  public RobotDataCommand.Robot robot() {
    return _robot;
  }

  @Override
  public Logger logger() {
    return _logger;
  }

  @Override
  public RobotDataCommand.Service service() {
    return _service;
  }

  Object _param;
  CmdRobotImpl _robot;

  Logger _logger;
  RobotDataCommand.Service _service;
}
