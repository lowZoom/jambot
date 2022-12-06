package luj.game.robot.internal.concurrent.instance.command;

import luj.game.robot.api.action.RobotDataCommand;

final class CmdServiceImpl implements RobotDataCommand.Service {

  @Override
  public RobotDataCommand.Random random() {
    return _random;
  }

  RobotDataCommand.Random _random;
}
