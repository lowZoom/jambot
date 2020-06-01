package luj.game.robot.internal.concurrent.instance.command.service;

import luj.cluster.api.actor.Tellable;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.internal.net.send.BotProtoSender;
import luj.game.robot.internal.start.botinstance.RobotState;
import luj.net.api.NetContext;
import luj.net.api.client.NetConnection;

public class CmdNetworkImpl implements RobotCommand.Network {

  public CmdNetworkImpl(NetContext lujnet, Tellable instanceRef,
      RobotState robotState, RobotProtoEncoder protoEncoder) {
    _lujnet = lujnet;
    _instanceRef = instanceRef;
    _robotState = robotState;
    _protoEncoder = protoEncoder;
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

  private final NetContext _lujnet;
  private final Tellable _instanceRef;

  private final RobotState _robotState;
  private final RobotProtoEncoder _protoEncoder;
}
