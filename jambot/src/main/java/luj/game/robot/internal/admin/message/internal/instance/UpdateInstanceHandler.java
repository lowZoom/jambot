package luj.game.robot.internal.admin.message.internal.instance;

import luj.game.robot.internal.admin.actor.AdminBotInfo;
import luj.game.robot.internal.admin.actor.BotAdminActor;
import luj.spring.anno.Internal;

@Internal
final class UpdateInstanceHandler implements BotAdminActor.Handler<UpdateInstanceMsg> {

  @Override
  public void onHandle(Context ctx) {
    BotAdminActor self = ctx.getActorState(this);
    UpdateInstanceMsg msg = ctx.getMessage(this);

    Integer index = msg.index();
    AdminBotInfo instance = self.getInstanceMap().computeIfAbsent(index, AdminBotInfo::new);

    instance.setConnected(msg.isConnected());
    instance.setStatus(msg.status());
  }
}
