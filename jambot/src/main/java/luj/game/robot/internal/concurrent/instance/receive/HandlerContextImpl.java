package luj.game.robot.internal.concurrent.instance.receive;

import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.concurrent.instance.command.CommandExecuteStarter;
import luj.game.robot.internal.net.send.BotProtoSender;
import luj.game.robot.internal.start.botinstance.RobotState;
import luj.net.api.NetContext;
import luj.net.api.client.NetConnection;

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
  public void disconnect() {
    _robotState.getConnection().close();
    _robotState.setConnection(null);
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
    new CommandExecuteStarter(_instanceRef, cmdType).start();
  }

  private final Object _proto;
  private final RobotState _robotState;

  private final NetContext _lujnet;
  private final RobotProtoEncoder _protoEncoder;

  private final ActorMessageHandler.Ref _instanceRef;
}
