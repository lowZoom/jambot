package luj.game.robot.internal.concurrent.instance.start;

import luj.cluster.api.actor.ActorPreStartHandler;
import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.net.send.BotProtoSender;
import luj.game.robot.internal.start.botinstance.RobotState;
import luj.net.api.NetContext;
import luj.net.api.client.NetConnection;

final class CreateContextImpl implements RobotCreateListener.Context {

  CreateContextImpl(RobotInstanceActor instanceActor, ActorPreStartHandler.Actor instanceRef,
      RobotState robotState, NetContext lujnet, RobotProtoEncoder protoEncoder) {
    _instanceActor = instanceActor;
    _instanceRef = instanceRef;
    _robotState = robotState;
    _lujnet = lujnet;
    _protoEncoder = protoEncoder;
  }

  @Override
  public String getRobotId() {
    return _instanceActor.getInstanceId();
  }

  @Override
  public void putData(Object data) {
    _robotState.getDataMap().put(data.getClass(), data);
  }

  @Override
  public void connect(String host, int port) {
    NetConnection conn = _lujnet.createConnection(host, port, _instanceRef);
    _robotState.setConnection(conn);
  }

  @Override
  public void send(Object proto) {
    new BotProtoSender(proto, _protoEncoder, _robotState.getConnection()).send();
  }

  private final RobotInstanceActor _instanceActor;
  private final ActorPreStartHandler.Actor _instanceRef;
  private final RobotState _robotState;

  private final NetContext _lujnet;
  private final RobotProtoEncoder _protoEncoder;
}
