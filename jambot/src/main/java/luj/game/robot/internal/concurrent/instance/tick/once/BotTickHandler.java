package luj.game.robot.internal.concurrent.instance.tick.once;

import luj.ava.spring.Internal;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.instance.tick.BotInstanceTicker;

@Internal
final class BotTickHandler implements RobotInstanceActor.Handler<BotTickMsg> {

  @Override
  public void onHandle(Context ctx) {
    RobotInstanceActor self = ctx.getActorState(this);

    Ref selfRef = ctx.getActorRef();
    new BotInstanceTicker(self.getRobotState(), selfRef, self.getDependency()).tick();
  }
}
