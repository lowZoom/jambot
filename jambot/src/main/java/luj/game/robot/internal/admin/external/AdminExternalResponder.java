package luj.game.robot.internal.admin.external;

import luj.bean.api.BeanContext;
import luj.bean.api.bean.Bean;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.admin.actor.AdminResponseMsg;

public class AdminExternalResponder {

  public AdminExternalResponder(Bean<?> rspBean, Tellable replyTo, BeanContext lujbean) {
    _rspBean = rspBean;
    _replyTo = replyTo;
    _lujbean = lujbean;
  }

  public void respond() {
    _replyTo.tell(_lujbean.create(AdminResponseMsg.class, (b, m) -> b
        .set(m::responseType, _rspBean.getBeanType())
        .set(m::responseData, _rspBean.getValueInstance())
    ));
  }

  private final Bean<?> _rspBean;

  private final Tellable _replyTo;
  private final BeanContext _lujbean;
}
