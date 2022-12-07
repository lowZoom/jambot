package luj.game.robot.internal.concurrent.instance.prestart;

import luj.game.robot.api.action.RobotCreateListener;

final class InternalImpl implements RobotCreateListener.Internal {

  @Override
  public RobotCreateListener.Network network() {
    return _network;
  }

  INetworkImpl _network;
}
