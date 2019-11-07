package luj.game.robot.internal.concurrent.parent.spawn;

import akka.actor.ActorRef;
import java.util.HashMap;
import java.util.List;
import luj.ava.spring.Internal;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.parent.RobotParentActor;
import luj.game.robot.internal.start.botinstance.RobotState;

@Internal
final class OnRobotSpawn implements RobotParentActor.Handler<RobotSpawnMsg> {

  @Override
  public void onHandle(Context ctx) {
    RobotSpawnMsg msg = ctx.getMessage(this);
    RobotParentActor parentActor = ctx.getActorState(this);

    RobotState robotState = new RobotState();
    robotState.setDataMap(new HashMap<>());

    RobotInstanceActor instanceActor = new RobotInstanceActor(robotState,
        msg.getRobotId(), parentActor.getInstanceDependency());

    List<ActorRef> robotList = parentActor.getRobotList();
    ActorRef robotRef = ctx.createActor(instanceActor);

    robotList.add(robotRef);
  }
}
