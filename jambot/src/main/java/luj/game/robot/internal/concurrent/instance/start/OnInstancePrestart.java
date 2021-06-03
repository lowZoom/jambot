package luj.game.robot.internal.concurrent.instance.start;

import luj.ava.spring.Internal;
import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.concurrent.instance.tick.loop.BotTickLoopMsg;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;
import luj.game.robot.internal.start.botinstance.RobotState;

@Internal
final class OnInstancePrestart implements RobotInstanceActor.PreStart {

  @Override
  public void onHandle(Context ctx) {
    RobotInstanceActor self = ctx.getActorState(this);
    RobotState robotState = self.getRobotState();

    Actor selfRef = ctx.getActor();
    RobotInstanceDependency dependency = self.getDependency();
    RobotInstanceInjectRoot rootBean = dependency.getInjectRoot();

    // 触发创建监听
    CreateContextImpl createCtx = new CreateContextImpl(selfRef, robotState,
        dependency.getLujnet(), rootBean.getProtoEncoder());

    RobotCreateListener createListener = rootBean.getCreateListener();
    createListener.onCreate(createCtx);

    // 开始心跳
    selfRef.tell(BotTickLoopMsg.INSTANCE);
  }
}
