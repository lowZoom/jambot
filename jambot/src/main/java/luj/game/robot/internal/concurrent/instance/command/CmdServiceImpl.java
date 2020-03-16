package luj.game.robot.internal.concurrent.instance.command;

import luj.game.robot.api.action.RobotCommand;

final class CmdServiceImpl implements RobotCommand.Service {

  CmdServiceImpl(RobotCommand.Random randomSvc) {
    _randomSvc = randomSvc;
  }

  @Override
  public RobotCommand.Random random() {
    return _randomSvc;
  }

  private final RobotCommand.Random _randomSvc;
}
