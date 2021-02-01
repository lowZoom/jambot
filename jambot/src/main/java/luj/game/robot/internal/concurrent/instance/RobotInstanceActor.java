package luj.game.robot.internal.concurrent.instance;

import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.start.botinstance.RobotState;

public class RobotInstanceActor {

  public interface Handler<M> extends ActorMessageHandler<RobotInstanceActor, M> {
    // NOOP
  }

  public interface PreStart extends ActorPreStartHandler<RobotInstanceActor> {
    // NOOP
  }

  public RobotInstanceActor(RobotState robotState, Integer index, int tickMsMin,
      int tickMsMax, RobotInstanceDependency dependency, Tellable adminRef) {
    _robotState = robotState;
    _index = index;
    _tickMsMin = tickMsMin;
    _tickMsMax = tickMsMax;
    _dependency = dependency;
    _adminRef = adminRef;
  }

  public RobotState getRobotState() {
    return _robotState;
  }

  public Integer getIndex() {
    return _index;
  }

  public int getTickMsMin() {
    return _tickMsMin;
  }

  public int getTickMsMax() {
    return _tickMsMax;
  }

  public RobotInstanceDependency getDependency() {
    return _dependency;
  }

  public Tellable getAdminRef() {
    return _adminRef;
  }

  private final RobotState _robotState;
  private final Integer _index;

  private final int _tickMsMin;
  private final int _tickMsMax;

  private final RobotInstanceDependency _dependency;
  private final Tellable _adminRef;
}
