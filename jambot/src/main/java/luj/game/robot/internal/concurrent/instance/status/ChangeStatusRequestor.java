package luj.game.robot.internal.concurrent.instance.status;

import luj.bean.api.BeanContext;
import luj.cluster.api.actor.Tellable;

public class ChangeStatusRequestor {

  public ChangeStatusRequestor(Tellable instanceRef, String newStatus, BeanContext lujbean) {
    _instanceRef = instanceRef;
    _newStatus = newStatus;
    _lujbean = lujbean;
  }

  public void request() {
    _instanceRef.tell(_lujbean.createBean(ChangeStatusMsg.class, (b, m) -> b
        .set(m::newStatus, _newStatus)
    ).getInstance());
  }

  private final Tellable _instanceRef;
  private final String _newStatus;

  private final BeanContext _lujbean;
}
