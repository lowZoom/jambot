package luj.game.robot.internal.concurrent.instance;

import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.internal.start.botinstance.RobotState;

public class RobotInstanceActor {

  public interface Handler<M> extends ActorMessageHandler<RobotInstanceActor, M> {
    // NOOP
  }

  public RobotInstanceActor(RobotState robotState, String instanceId,
      RobotInstanceDependency dependency) {
    _robotState = robotState;
    _instanceId = instanceId;
    _dependency = dependency;
  }

  public RobotState getRobotState() {
    return _robotState;
  }

  public String getInstanceId() {
    return _instanceId;
  }

  public RobotInstanceDependency getDependency() {
    return _dependency;
  }

  private final RobotState _robotState;
  private final String _instanceId;

  private final RobotInstanceDependency _dependency;
}
