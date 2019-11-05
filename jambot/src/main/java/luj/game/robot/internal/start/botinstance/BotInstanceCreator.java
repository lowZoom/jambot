package luj.game.robot.internal.start.botinstance;

import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.net.api.LujNet;
import luj.net.api.NetConnection;

public class BotInstanceCreator {

  public BotInstanceCreator(String host, int port, RobotProtoEncoder protoEncoder) {
    _host = host;
    _port = port;
    _protoEncoder = protoEncoder;
  }

  public RobotStartListener.Robot create() {
    NetConnection conn = LujNet.createConnection(_host, _port);
    return new RobotImpl(conn, _protoEncoder);
  }

  private final String _host;
  private final int _port;

  private final RobotProtoEncoder _protoEncoder;
}
