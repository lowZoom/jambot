package luj.game.robot.api;

import akka.actor.ActorRef;
import java.util.function.Function;

public interface RobotContext {

  interface Start {
  }

  @Deprecated
  void start(String host, int port);

  void start(Function<Start, Start> config);

  void sendMessage(String msgKey, Object msg, ActorRef sender);
}
