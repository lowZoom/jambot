package luj.game.robot.internal.start.listener;

import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.internal.start.botinstance.BotInstanceCreator;
import luj.net.api.NetContext;

final class StartContextImpl implements RobotStartListener.Context {

  StartContextImpl(NetContext lujnet, RobotProtoEncoder protoEncoder) {
    _lujnet = lujnet;
    _protoEncoder = protoEncoder;
  }

  @Override
  public RobotStartListener.Robot createRobot(String host, int port) {
    return new BotInstanceCreator(host, port, _lujnet, _protoEncoder).create();
  }

  private final NetContext _lujnet;

  private final RobotProtoEncoder _protoEncoder;
}
