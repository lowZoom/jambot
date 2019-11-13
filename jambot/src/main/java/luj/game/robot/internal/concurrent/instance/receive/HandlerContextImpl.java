package luj.game.robot.internal.concurrent.instance.receive;

import java.time.Duration;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.concurrent.instance.command.BotExecuteCommandMsg;
import luj.game.robot.internal.net.send.BotProtoSender;
import luj.game.robot.internal.start.botinstance.RobotState;
import luj.net.api.NetConnection;
import luj.net.api.NetContext;

final class HandlerContextImpl implements RobotProtoHandler.Context {

  HandlerContextImpl(Object proto, RobotState robotState, NetContext lujnet,
      RobotProtoEncoder protoEncoder, ActorMessageHandler.Ref instanceRef) {
    _proto = proto;
    _robotState = robotState;
    _lujnet = lujnet;
    _protoEncoder = protoEncoder;
    _instanceRef = instanceRef;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <P> P proto(RobotProtoHandler<P> handler) {
    return (P) _proto;
  }

  @Override
  public void connect(String host, int port) {
    NetConnection oldConn = _robotState.getConnection();
    oldConn.close();
    _robotState.setConnection(_lujnet.createConnection(host, port, oldConn.getApplicationParam()));
  }

  @Override
  public void send(Object proto) {
    new BotProtoSender(proto, _protoEncoder, _robotState.getConnection()).send();
  }

  @Override
  public void putData(Object data) {
    _robotState.getDataMap().put(data.getClass(), data);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <D> D getData(Class<D> dataType) {
    return (D) _robotState.getDataMap().get(dataType);
  }

  @Override
  public void executeCommand(Class<? extends RobotCommand> cmdType) {
    BotExecuteCommandMsg msg = new BotExecuteCommandMsg(cmdType);
    _instanceRef.tell(msg, Duration.ofMillis(1000));
  }

  private final Object _proto;
  private final RobotState _robotState;

  private final NetContext _lujnet;
  private final RobotProtoEncoder _protoEncoder;

  private final ActorMessageHandler.Ref _instanceRef;
}
