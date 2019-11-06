package luj.game.robot.internal.net.receive;

import luj.game.robot.api.proto.RobotProtoHandler;

final class HandlerContextImpl implements RobotProtoHandler.Context {

  HandlerContextImpl(Object proto) {
    _proto = proto;
  }

  @Override
  public <P> P proto(RobotProtoHandler<P> handler) {
    return (P) _proto;
  }

  @Override
  public void connect(String host, int port) {

  }

  @Override
  public void send(Object proto) {

  }

  private final Object _proto;
}
