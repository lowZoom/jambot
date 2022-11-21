package luj.game.robot.internal.concurrent.instance.command;

import luj.game.robot.api.action.RobotCommand;

final class CmdServiceImpl implements RobotCommand.Service {

  @Override
  public RobotCommand.Random random() {
    return _random;
  }

  RobotCommand.Random _random;
}
