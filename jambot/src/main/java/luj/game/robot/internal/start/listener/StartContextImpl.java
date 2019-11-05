package luj.game.robot.internal.start.listener;

import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.internal.start.botinstance.BotInstanceCreator;

final class StartContextImpl implements RobotStartListener.Context {

  StartContextImpl(RobotProtoEncoder protoEncoder) {
    _protoEncoder = protoEncoder;
  }

  @Override
  public RobotStartListener.Robot createRobot(String host, int port) {
    return new BotInstanceCreator(host, port, _protoEncoder).create();
  }

  private final RobotProtoEncoder _protoEncoder;
}
