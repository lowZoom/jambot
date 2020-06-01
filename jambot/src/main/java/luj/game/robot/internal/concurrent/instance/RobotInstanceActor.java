package luj.game.robot.internal.concurrent.instance;

import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.game.robot.internal.start.botinstance.RobotState;

public class RobotInstanceActor {

  public interface Handler<M> extends ActorMessageHandler<RobotInstanceActor, M> {
    // NOOP
  }

  public interface PreStart extends ActorPreStartHandler<RobotInstanceActor> {
    // NOOP
  }

  public RobotInstanceActor(RobotState robotState, String instanceId,
      int index, RobotInstanceDependency dependency) {
    _robotState = robotState;
    _instanceId = instanceId;
    _index = index;
    _dependency = dependency;
  }

  public RobotState getRobotState() {
    return _robotState;
  }

  @Deprecated
  public String getInstanceId() {
    return _instanceId;
  }

  public int getIndex() {
    return _index;
  }

  public RobotInstanceDependency getDependency() {
    return _dependency;
  }

  private final RobotState _robotState;

  @Deprecated
  private final String _instanceId;
  private final int _index;

  private final RobotInstanceDependency _dependency;
}
