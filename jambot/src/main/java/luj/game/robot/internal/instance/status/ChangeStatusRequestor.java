package luj.game.robot.internal.instance.status;

import luj.bean.api.BeanContext;
import luj.bean.api.bean.Bean;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.concurrent.instance.status.ChangeStatusMsg;

public class ChangeStatusRequestor {

  public ChangeStatusRequestor(Tellable instanceRef, String newStatus, BeanContext lujbean) {
    _instanceRef = instanceRef;
    _newStatus = newStatus;
    _lujbean = lujbean;
  }

  /**
   * @see luj.game.robot.internal.concurrent.instance.status.ChangeStatusHandler#onHandle
   */
  public void request() {
//    System.out.println("****ChangeStatusRequestorChangeStatusRequestor " + _newStatus);

    Bean<ChangeStatusMsg> msg = _lujbean.createBean(ChangeStatusMsg.class);
    msg.setField(b -> b::newStatus, _newStatus);
    _instanceRef.tell(msg.getValueInstance());
  }

  private final Tellable _instanceRef;
  private final String _newStatus;

  private final BeanContext _lujbean;
}
