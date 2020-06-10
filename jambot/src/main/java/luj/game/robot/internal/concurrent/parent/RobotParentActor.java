package luj.game.robot.internal.concurrent.parent;

import akka.actor.ActorRef;
import java.util.List;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.actor.Tellable;
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

  public int getNextIndex() {
    return _nextIndex;
  }

  public void setNextIndex(int nextIndex) {
    _nextIndex = nextIndex;
  }

  public Tellable getAdminRef() {
    return _adminRef;
  }

  public void setAdminRef(Tellable adminRef) {
    _adminRef = adminRef;
  }

  public RobotInstanceDependency getInstanceDependency() {
    return _instanceDependency;
  }

  public void setInstanceDependency(RobotInstanceDependency instanceDependency) {
    _instanceDependency = instanceDependency;
  }

  private List<ActorRef> _robotList;
  private int _nextIndex;

  private Tellable _adminRef;
  private RobotInstanceDependency _instanceDependency;
}
