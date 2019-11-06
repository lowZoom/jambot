package luj.game.robot.internal.net.receive;

import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.net.send.BotProtoSender;
import luj.game.robot.internal.start.botinstance.RobotState;
import luj.net.api.NetContext;

final class HandlerContextImpl implements RobotProtoHandler.Context {

  HandlerContextImpl(Object proto, RobotState robotState,
      NetContext lujnet, RobotProtoEncoder protoEncoder) {
    _proto = proto;
    _robotState = robotState;
    _lujnet = lujnet;
    _protoEncoder = protoEncoder;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <P> P proto(RobotProtoHandler<P> handler) {
    return (P) _proto;
  }

  @Override
  public void connect(String host, int port) {
    _robotState.getConnection().close();
    _robotState.setConnection(_lujnet.createConnection(host, port, _robotState));
  }

  @Override
  public void send(Object proto) {
    new BotProtoSender(proto, _protoEncoder, _robotState.getConnection()).send();
  }

  private final Object _proto;
  private final RobotState _robotState;

  private final NetContext _lujnet;
  private final RobotProtoEncoder _protoEncoder;
}
