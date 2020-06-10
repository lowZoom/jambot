package luj.game.robot.internal.admin.message.external;

import java.util.List;
import java.util.stream.Collectors;
import luj.ava.spring.Internal;
import luj.bean.api.BeanContext;
import luj.bean.api.bean.Bean;
import luj.game.robot.api.admin.message.InstanceListReq;
import luj.game.robot.api.admin.message.InstanceListRsp;
import luj.game.robot.internal.admin.actor.AdminBotInfo;
import luj.game.robot.internal.admin.actor.BotAdminActor;
import luj.game.robot.internal.admin.external.AdminExternalResponder;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class InstanceListHandler implements BotAdminActor.Handler<InstanceListReq> {

  @Override
  public void onHandle(Context ctx) {
    BotAdminActor self = ctx.getActorState(this);

    List<InstanceListRsp.Bot> instanceList = self.getInstanceMap().values().stream()
        .map(this::encodeInstance)
        .collect(Collectors.toList());

    Bean<?> rsp = _lujbean.createBean(InstanceListRsp.class, (f, b) -> f
        .set(b::instanceList, instanceList)
    );
    new AdminExternalResponder(rsp, ctx.getSenderRef(), _lujbean).respond();
  }

  private InstanceListRsp.Bot encodeInstance(AdminBotInfo info) {
    return _lujbean.createBean(InstanceListRsp.Bot.class, (f, b) -> f
        .set(b::index, info.getIndex())
        .set(b::connected, Boolean.FALSE)
    ).getInstance();
  }

  @Autowired
  private BeanContext _lujbean;
}
