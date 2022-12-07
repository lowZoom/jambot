package luj.game.robot.internal.concurrent.instance.prestart;

import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.concurrent.instance.tick.loop.BotTickLoopMsg;
import luj.spring.anno.Internal;

@Internal
final class OnInstancePrestart implements RobotInstanceActor.PreStart {

  @Override
  public void onHandle(Context ctx) {
    Actor selfRef = ctx.getActor();

    // 触发创建监听
    invokeBorn(ctx);

    // 开始心跳
    selfRef.tell(BotTickLoopMsg.INSTANCE);
  }

  private void invokeBorn(Context ctx) {
    RobotInstanceActor self = ctx.getActorState(this);
    var network = new INetworkImpl();
    network._instanceRef = ctx.getActor();

    var internal = new InternalImpl();
    internal._network = network;

    var createCtx = new CreateContextImpl();
    createCtx._robotState = self.getRobotState();
    createCtx._internal = internal;

    RobotInstanceDependency dep = self.getDependency();
    for (RobotCreateListener listener : dep.getCreateList()) {
      listener.onCreate(createCtx);
    }
  }
}
