package luj.game.robot.api;

import akka.actor.ActorRef;

public interface RobotContext {

  void start(String host, int port);

  void sendMessage(String msgKey, Object msg, ActorRef sender);
}
