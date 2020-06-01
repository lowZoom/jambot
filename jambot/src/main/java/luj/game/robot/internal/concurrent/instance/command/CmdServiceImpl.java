package luj.game.robot.internal.concurrent.instance.command;

import luj.game.robot.api.action.RobotCommand;

final class CmdServiceImpl implements RobotCommand.Service {

  CmdServiceImpl(RobotCommand.Network network, RobotCommand.Random random) {
    _network = network;
    _random = random;
  }

  @Override
  public RobotCommand.Network network() {
    return _network;
  }

  @Override
  public RobotCommand.Random random() {
    return _random;
  }

  private final RobotCommand.Network _network;

  private final RobotCommand.Random _random;
}
