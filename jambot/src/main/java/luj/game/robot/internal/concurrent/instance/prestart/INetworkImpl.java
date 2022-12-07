package luj.game.robot.internal.concurrent.instance.prestart;

import luj.cluster.api.actor.Tellable;
import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.internal.concurrent.instance.network.receive.BotReceiveProtoMsg;

final class INetworkImpl implements RobotCreateListener.Network {

  @Override
  public void receiveProto(Object proto, Object param) {
    _instanceRef.tell(new BotReceiveProtoMsg(proto, param));
  }

  Tellable _instanceRef;
}
