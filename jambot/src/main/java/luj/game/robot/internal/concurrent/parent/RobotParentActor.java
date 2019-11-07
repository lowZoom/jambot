package luj.game.robot.internal.concurrent.parent;

import akka.actor.ActorRef;
import java.util.List;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;

public class RobotParentActor {

  public interface Handler<M> extends ActorMessageHandler<RobotParentActor, M> {
    // NOOP
  }

  public List<ActorRef> getRobotList() {
    return _robotList;
  }

  public void setRobotList(List<ActorRef> robotList) {
    _robotList = robotList;
  }

  public RobotInstanceDependency getInstanceDependency() {
    return _instanceDependency;
  }

  public void setInstanceDependency(RobotInstanceDependency instanceDependency) {
    _instanceDependency = instanceDependency;
  }

  private List<ActorRef> _robotList;

  private RobotInstanceDependency _instanceDependency;
}
