package luj.game.robot.internal.net.receive;

import io.netty.buffer.ByteBuf;
import luj.game.robot.api.proto.RobotProtoDecoder;

final class DecodeContextImpl implements RobotProtoDecoder.Context {

  DecodeContextImpl(ByteBuf data) {
    _data = data;
  }

  @Override
  public ByteBuf data() {
    return _data;
  }

  private final ByteBuf _data;
}
