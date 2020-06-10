package luj.game.robot.internal.concurrent.parent.spawn;

import luj.game.robot.internal.instance.action.BotAction;

public class RobotSpawnMsg {

  public RobotSpawnMsg(String robotId, BotAction action) {
    _robotId = robotId;
    _action = action;
  }

  @Deprecated
  public String getRobotId() {
    return _robotId;
  }

  public BotAction getAction() {
    return _action;
  }

  @Deprecated
  private final String _robotId;

  private final BotAction _action;
}
