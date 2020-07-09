package luj.game.robot.internal.concurrent.instance.tick;

import java.time.Duration;
import java.util.Random;
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

    Duration nextTick = getNextTickTime(self);
    selfRef.tell(BotTickMsg.INSTANCE, nextTick);
  }

  private Duration getNextTickTime(RobotInstanceActor self) {
    int min = self.getTickMsMin();
    int max = self.getTickMsMax();

    int result = min + RAND.nextInt(max - min);
    return Duration.ofMillis(result);
  }

  private static final Random RAND = new Random();
}
