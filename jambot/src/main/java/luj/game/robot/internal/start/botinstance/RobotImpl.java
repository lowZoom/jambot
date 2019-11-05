package luj.game.robot.internal.start.botinstance;

import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.internal.net.send.BotProtoSender;
import luj.net.api.NetConnection;

final class RobotImpl implements RobotStartListener.Robot {

  RobotImpl(NetConnection connection, RobotProtoEncoder protoEncoder) {
    _connection = connection;
    _protoEncoder = protoEncoder;
  }

  @Override
  public void send(Object proto) {
    new BotProtoSender(proto, _protoEncoder, _connection).send();
  }

  private final NetConnection _connection;

  private final RobotProtoEncoder _protoEncoder;
}
