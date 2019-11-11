package luj.game.robot.internal.concurrent.instance.receive;

public class BotReceiveProtoMsg {

  public BotReceiveProtoMsg(byte[] protoData) {
    _protoData = protoData;
  }

  public byte[] getProtoData() {
    return _protoData;
  }

  private final byte[] _protoData;
}
