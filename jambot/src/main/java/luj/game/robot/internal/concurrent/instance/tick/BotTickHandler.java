package luj.game.robot.internal.concurrent.instance.tick;

import java.time.Duration;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.instance.tick.BotInstanceTicker;
import org.springframework.stereotype.Component;

@Component
final class BotTickHandler implements RobotInstanceActor.Handler<BotTickMsg> {

  @Override
  public void onHandle(Context ctx) {
    RobotInstanceActor self = ctx.getActorState(this);
    Ref selfRef = ctx.getActorRef();

    new BotInstanceTicker(self.getRobotState(), selfRef, self.getDependency()).tick();
    selfRef.tell(BotTickMsg.INSTANCE, Duration.ofSeconds(2));
  }
}
