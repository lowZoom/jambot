package luj.game.robot.internal.concurrent.instance.status;

import luj.ava.spring.Internal;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.instance.status.BotStatusChanger;
import luj.game.robot.internal.start.botinstance.RobotState;

@Internal
final class ChangeStatusHandler implements RobotInstanceActor.Handler<ChangeStatusMsg> {

  @Override
  public void onHandle(Context ctx) {
    RobotInstanceActor self = ctx.getActorState(this);
    ChangeStatusMsg msg = ctx.getMessage(this);

    String newStatus = msg.newStatus();
    RobotState instanceState = self.getRobotState();

    Ref selfRef = ctx.getActorRef();
    new BotStatusChanger(instanceState, newStatus, selfRef).change();
  }
}
