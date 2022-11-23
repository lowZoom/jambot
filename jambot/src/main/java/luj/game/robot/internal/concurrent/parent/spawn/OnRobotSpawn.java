package luj.game.robot.internal.concurrent.parent.spawn;

import akka.actor.ActorRef;
import java.util.HashMap;
import java.util.LinkedList;
import luj.ava.spring.Internal;
import luj.bean.api.BeanContext;
import luj.game.robot.internal.admin.message.internal.instance.UpdateInstanceMsg;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.parent.RobotParentActor;
import luj.game.robot.internal.start.botinstance.RobotState;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class OnRobotSpawn implements RobotParentActor.Handler<RobotSpawnMsg> {

  /**
   * @see luj.game.robot.internal.concurrent.instance.start.OnInstancePrestart#onHandle
   */
  @Override
  public void onHandle(Context ctx) {
    RobotParentActor self = ctx.getActorState(this);
    RobotSpawnMsg msg = ctx.getMessage(this);

    RobotInstanceActor instanceActor = createInstance(self, msg);
    ActorRef robotRef = ctx.createActor(instanceActor);
    self.getRobotList().add(robotRef);

    updateAdmin(self, instanceActor);
  }

  private RobotInstanceActor createInstance(RobotParentActor self, RobotSpawnMsg msg) {
    RobotState robotState = new RobotState();
    robotState.setDataMap(new HashMap<>());
    robotState.setReceiveHistory(new LinkedList<>());

    robotState.setStatus(msg.getInitStatus());
    robotState.setStatusMap(msg.getStatusMap());

//    robotState.setCurAction(msg.getAction());
    robotState.setStepIndex(-1);

    int robotIndex = self.getNextIndex();
    self.setNextIndex(robotIndex + 1);

    return new RobotInstanceActor(robotState, robotIndex, msg.getTickMsMin(),
        msg.getTickMsMax(), self.getInstanceDependency(), self.getAdminRef());
  }

  private void updateAdmin(RobotParentActor self, RobotInstanceActor instance) {
    self.getAdminRef().tell(_lujbean.createBean(UpdateInstanceMsg.class, (b, m) -> b
        .set(m::index, instance.getIndex())
    ).getInstance());
  }

  @Autowired
  private BeanContext _lujbean;
}
