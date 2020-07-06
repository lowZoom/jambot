package luj.game.robot.internal.net.disconnect;

import luj.bean.api.BeanContext;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.api.network.RobotDisconnectListener;
import luj.game.robot.internal.concurrent.instance.status.ChangeStatusRequestor;

final class ContextImpl implements RobotDisconnectListener.Context {

  @Override
  public void changeStatus(String status) {
    new ChangeStatusRequestor(_instanceRef, status, _lujbean).request();
  }

  Tellable _instanceRef;

  BeanContext _lujbean;
}
