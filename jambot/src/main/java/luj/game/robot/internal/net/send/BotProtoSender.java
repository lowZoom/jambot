package luj.game.robot.internal.net.send;

import luj.game.robot.api.proto.RobotProtoEncoder;

@Deprecated
public class BotProtoSender {

  public BotProtoSender(Object proto, RobotProtoEncoder protoEncoder) {
    _proto = proto;
    _protoEncoder = protoEncoder;
  }

  public void send() {
    EncodeContextImpl ctx = new EncodeContextImpl(_proto);
    byte[] data = _protoEncoder.encode(ctx);

//    _connection.send(data);
  }

  private final Object _proto;
  private final RobotProtoEncoder _protoEncoder;
}
