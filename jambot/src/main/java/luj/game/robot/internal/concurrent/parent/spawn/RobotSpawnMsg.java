package luj.game.robot.internal.concurrent.parent.spawn;

public class RobotSpawnMsg {

  public RobotSpawnMsg(String robotId) {
    _robotId = robotId;
  }

  public String getRobotId() {
    return _robotId;
  }

  private final String _robotId;
}
