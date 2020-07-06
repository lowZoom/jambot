package luj.game.robot.internal.concurrent.instance.network.disconnect;

import luj.ava.spring.Internal;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.net.disconnect.BotDisconnectInvoker;

@Internal
final class OnNetDisconnect implements RobotInstanceActor.Handler<BotNetDisconnectMsg> {

  @Override
  public void onHandle(Context ctx) {
    RobotInstanceActor self = ctx.getActorState(this);

    new BotDisconnectInvoker(self.getDependency(), ctx.getActorRef()).invoke();
  }
}
