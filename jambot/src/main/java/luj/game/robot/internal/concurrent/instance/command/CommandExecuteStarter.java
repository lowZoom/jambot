package luj.game.robot.internal.concurrent.instance.command;

import com.google.common.collect.ImmutableMap;
import java.time.Duration;
import java.util.Random;
import luj.cluster.api.actor.ActorMessageHandler;

public class CommandExecuteStarter {

  public CommandExecuteStarter(ActorMessageHandler.Ref instanceRef, Class<?> commandType) {
    _instanceRef = instanceRef;
    _commandType = commandType;
  }

  public void start() {
    BotExecuteCommandMsg msg = new BotExecuteCommandMsg(_commandType, ImmutableMap.of());
    int delay = 250 + RAND.nextInt(5000);

    _instanceRef.tell(msg, Duration.ofMillis(delay));
  }

  private static final Random RAND = new Random();

  private final ActorMessageHandler.Ref _instanceRef;
  private final Class<?> _commandType;
}
