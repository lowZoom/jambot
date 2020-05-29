package luj.game.robot.admin.internal.cluster.request.actor;

import akka.actor.ActorRef;
import luj.ava.spring.Internal;

@Internal
public class RequestWaitRefHolder {

  public ActorRef getActorRef() {
    return _actorRef;
  }

  public void setActorRef(ActorRef actorRef) {
    _actorRef = actorRef;
  }

  private ActorRef _actorRef;
}
