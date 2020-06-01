package luj.game.robot.internal.concurrent.parent.spawn;

import luj.game.robot.internal.instance.action.BotAction;

public class RobotSpawnMsg {

  public RobotSpawnMsg(String robotId, int index, BotAction action) {
    _robotId = robotId;
    _index = index;
    _action = action;
  }

  @Deprecated
  public String getRobotId() {
    return _robotId;
  }

  public int getIndex() {
    return _index;
  }

  public BotAction getAction() {
    return _action;
  }

  @Deprecated
  private final String _robotId;

  private final int _index;
  private final BotAction _action;
}
