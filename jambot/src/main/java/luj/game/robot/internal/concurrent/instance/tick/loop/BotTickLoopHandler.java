package luj.game.robot.internal.concurrent.instance.tick.loop;

import java.time.Duration;
import java.util.Random;
import luj.ava.spring.Internal;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.tick.once.BotTickMsg;

@Internal
final class BotTickLoopHandler implements RobotInstanceActor.Handler<BotTickLoopMsg> {

  @Override
  public void onHandle(Context ctx) {
    RobotInstanceActor self = ctx.getActorState(this);

    Ref selfRef = ctx.getActorRef();
    selfRef.tell(BotTickMsg.INSTANCE);

    Duration nextTick = getNextTickTime(self);
    selfRef.tell(BotTickLoopMsg.INSTANCE, nextTick);
  }

  private Duration getNextTickTime(RobotInstanceActor self) {
    int min = self.getTickMsMin();
    int max = self.getTickMsMax();

    int result = min + RAND.nextInt(max - min + 1);
    return Duration.ofMillis(result);
  }

  private static final Random RAND = new Random();
}
