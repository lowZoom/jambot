package luj.game.robot.internal.net.receive;

import luj.game.robot.api.proto.RobotProtoDecoder;

final class DecodeContextImpl implements RobotProtoDecoder.Context {

  DecodeContextImpl(byte[] data) {
    _data = data;
  }

  @Override
  public byte[] data() {
    return _data;
  }

  private final byte[] _data;
}
