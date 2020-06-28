package luj.game.robot.internal.concurrent.instance.command.service;

import luj.cluster.api.actor.Tellable;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.net.send.BotProtoSender;
import luj.game.robot.internal.start.botinstance.RobotState;
import luj.net.api.NetContext;
import luj.net.api.client.NetConnection;

public class CmdNetworkImpl implements RobotCommand.Network {

  public CmdNetworkImpl(Tellable instanceRef, RobotState robotState,
      RobotInstanceDependency instanceDep) {
    _instanceRef = instanceRef;
    _robotState = robotState;
    _instanceDep = instanceDep;
  }

  @Override
  public void connect(String host, int port) {
    NetContext lujnet = _instanceDep.getLujnet();
    NetConnection conn = lujnet.createConnection(host, port, _instanceRef);
    _robotState.setConnection(conn);
  }

  @Override
  public void send(Object proto) {
    RobotProtoEncoder protoEncoder = _instanceDep.getInjectRoot().getProtoEncoder();
    new BotProtoSender(proto, protoEncoder, _robotState.getConnection()).send();
  }

  @Override
  public RobotCommand.Http http() {
    return new NetworkHttpImpl(_instanceRef, _instanceDep);
  }

  private final Tellable _instanceRef;
  private final RobotState _robotState;

  private final RobotInstanceDependency _instanceDep;
}
