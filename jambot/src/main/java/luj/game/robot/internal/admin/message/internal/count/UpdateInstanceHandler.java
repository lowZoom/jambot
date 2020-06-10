package luj.game.robot.internal.admin.message.internal.count;

import luj.ava.spring.Internal;
import luj.game.robot.internal.admin.actor.AdminBotInfo;
import luj.game.robot.internal.admin.actor.BotAdminActor;

@Internal
final class UpdateInstanceHandler implements BotAdminActor.Handler<UpdateInstanceMsg> {

  @Override
  public void onHandle(Context ctx) {
    BotAdminActor self = ctx.getActorState(this);
    UpdateInstanceMsg msg = ctx.getMessage(this);

    Integer index = msg.index();
    AdminBotInfo instance = self.getInstanceMap().computeIfAbsent(index, AdminBotInfo::new);
  }
}
