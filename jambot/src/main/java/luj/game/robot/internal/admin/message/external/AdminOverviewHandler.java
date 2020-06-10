package luj.game.robot.internal.admin.message.external;

import luj.ava.spring.Internal;
import luj.bean.api.BeanContext;
import luj.bean.api.bean.Bean;
import luj.game.robot.api.admin.message.AdminOverviewReq;
import luj.game.robot.api.admin.message.AdminOverviewRsp;
import luj.game.robot.internal.admin.actor.BotAdminActor;
import luj.game.robot.internal.admin.external.AdminExternalResponder;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class AdminOverviewHandler implements BotAdminActor.Handler<AdminOverviewReq> {

  @Override
  public void onHandle(Context ctx) {
    BotAdminActor self = ctx.getActorState(this);

    Bean<?> rsp = _lujbean.createBean(AdminOverviewRsp.class, (b, r) -> b
        .set(r::robotCount, self.getInstanceMap().size())
    );
    new AdminExternalResponder(rsp, ctx.getSenderRef(), _lujbean).respond();
  }

  @Autowired
  private BeanContext _lujbean;
}
