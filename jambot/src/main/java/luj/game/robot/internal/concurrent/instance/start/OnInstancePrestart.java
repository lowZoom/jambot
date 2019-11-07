package luj.game.robot.internal.concurrent.instance.start;

import luj.ava.spring.Internal;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;
import luj.game.robot.internal.start.botinstance.RobotState;

@Internal
final class OnInstancePrestart implements ActorPreStartHandler<RobotInstanceActor> {

  @Override
  public void onHandle(Context ctx) {
    RobotInstanceActor instanceActor = ctx.getActorState(this);
    RobotState robotState = instanceActor.getRobotState();
    robotState.setInstanceRef(ctx.getActorRef());

    RobotInstanceDependency dependency = instanceActor.getDependency();
    RobotInstanceInjectRoot rootBean = dependency.getInjectRoot();

    CreateContextImpl createCtx = new CreateContextImpl(instanceActor,
        robotState, dependency.getLujnet(), rootBean.getProtoEncoder());

    RobotCreateListener createListener = rootBean.getCreateListener();
    createListener.onCreate(createCtx);
  }
}
