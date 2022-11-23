package luj.game.robot.internal.concurrent.instance.command;

import java.time.Duration;
import java.util.Random;
import luj.cluster.api.actor.ActorMessageHandler;


@Deprecated //FIXME: 不应该在内部写死间隔
public class CommandExecuteStarter {

  public CommandExecuteStarter(ActorMessageHandler.Ref instanceRef, Class<?> commandType) {
    _instanceRef = instanceRef;
    _commandType = commandType;
  }

  public void start() {
    BotExecuteCommandMsg msg = new BotExecuteCommandMsg(_commandType.getName(), null);
    int delay = 250 + RAND.nextInt(5000);

    _instanceRef.tell(msg, Duration.ofMillis(delay));
  }

  private static final Random RAND = new Random();

  private final ActorMessageHandler.Ref _instanceRef;
  private final Class<?> _commandType;
}
