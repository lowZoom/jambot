package luj.game.robot.internal.concurrent.instance.admin;

import luj.game.robot.internal.admin.message.internal.instance.UpdateInstanceMsg;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.start.botinstance.RobotState;
import luj.spring.anno.Internal;

@Internal
final class UpdateAdminHandler implements RobotInstanceActor.Handler<UpdateAdminMsg> {

  @Override
  public void onHandle(Context ctx) {
    RobotInstanceActor self = ctx.getActorState(this);
    RobotState instance = self.getRobotState();

    self.getAdminRef().tell(new UpdateInstanceMsg(-1, false, instance.getStatus()));
  }
}
