package luj.game.robot.internal.start.botinstance;

import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.net.api.NetConnection;
import luj.net.api.NetContext;

public class BotInstanceCreator {

  public BotInstanceCreator(String host, int port, NetContext lujnet,
      RobotProtoEncoder protoEncoder) {
    _host = host;
    _port = port;

    _lujnet = lujnet;
    _protoEncoder = protoEncoder;
  }

  public RobotStartListener.Robot create() {
    RobotState robotState = new RobotState();

    NetConnection conn = _lujnet.createConnection(_host, _port, robotState);
    robotState.setConnection(conn);

    return new RobotImpl(conn, _protoEncoder);
  }

  private final String _host;
  private final int _port;

  private final NetContext _lujnet;
  private final RobotProtoEncoder _protoEncoder;
}
