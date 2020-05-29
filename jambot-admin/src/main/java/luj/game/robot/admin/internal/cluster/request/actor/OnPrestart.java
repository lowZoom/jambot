package luj.game.robot.admin.internal.cluster.request.actor;

import org.springframework.stereotype.Component;

@Component
final class OnPrestart implements RequestWaitActor.PreStart {

  @Override
  public void onHandle(Context ctx) {
    RequestWaitActor self = ctx.getActorState(this);

    RequestWaitRefHolder waitHolder = self.getWaitHolder();
    waitHolder.setActorRef(ctx.getActor().getAkkaRef());
  }
}
