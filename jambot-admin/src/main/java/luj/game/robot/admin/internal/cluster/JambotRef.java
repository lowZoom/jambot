package luj.game.robot.admin.internal.cluster;

import akka.actor.ActorRef;

public interface JambotRef {

  void sendMessage(Object msg, Class<?> msgType, ActorRef sender);
}
