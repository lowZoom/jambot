package luj.game.robot.internal.concurrent.instance.status;

import luj.ava.spring.Internal;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.admin.UpdateAdminMsg;
import luj.game.robot.internal.start.botinstance.RobotState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Internal
final class ChangeStatusHandler implements RobotInstanceActor.Handler<ChangeStatusMsg> {

  @Override
  public void onHandle(Context ctx) {
    RobotInstanceActor self = ctx.getActorState(this);
    ChangeStatusMsg msg = ctx.getMessage(this);

    String newStatus = msg.newStatus();
    RobotState instanceState = self.getRobotState();
    LOG.debug("状态切换：{} -> {}", instanceState.getStatus(), newStatus);

    instanceState.setStatus(newStatus);
    instanceState.setActionIndex(0);
    instanceState.setStepIndex(-1);
    instanceState.setCurStep(null);

    ctx.getActorRef().tell(UpdateAdminMsg.INSTANCE);
  }

  private static final Logger LOG = LoggerFactory.getLogger(ChangeStatusHandler.class);
}