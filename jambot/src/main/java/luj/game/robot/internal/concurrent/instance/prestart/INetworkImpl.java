package luj.game.robot.internal.concurrent.instance.prestart;

import luj.cluster.api.actor.Tellable;
import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.internal.concurrent.instance.network.receive.BotReceiveProtoMsg;

final class INetworkImpl implements RobotCreateListener.Network {

  @Override
  public void receiveProto(String protoKey, Object protoObj, Object param) {
    _instanceRef.tell(new BotReceiveProtoMsg(protoKey, protoObj, param));
  }

  Tellable _instanceRef;
}
