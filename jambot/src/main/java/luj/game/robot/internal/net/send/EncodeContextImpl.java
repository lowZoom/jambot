package luj.game.robot.internal.net.send;

import luj.game.robot.api.proto.RobotProtoEncoder;

final class EncodeContextImpl implements RobotProtoEncoder.Context {

  EncodeContextImpl(Object proto) {
    _proto = proto;
  }

  @Override
  public <T> T proto() {
    return (T) _proto;
  }

  private final Object _proto;
}
