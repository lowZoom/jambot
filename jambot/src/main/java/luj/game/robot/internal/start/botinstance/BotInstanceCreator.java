package luj.game.robot.internal.start.botinstance;

import static com.google.common.base.Preconditions.checkState;

import luj.cluster.api.node.NodeStartListener;
import luj.game.robot.internal.concurrent.parent.spawn.RobotSpawnMsg;
import luj.game.robot.internal.instance.action.BotAction;

public class BotInstanceCreator {

  public BotInstanceCreator(String robotId, int index, BotAction action,
      NodeStartListener.Actor parentRef) {
    _robotId = robotId;
    _index = index;
    _action = action;
    _parentRef = parentRef;
  }

  public void create() {
    if (_action != null) {
      checkState(!_action.getStepList().isEmpty(), _action.getName());
    }

    RobotSpawnMsg msg = new RobotSpawnMsg(_robotId, _index, _action);
    _parentRef.tell(msg);
  }

  @Deprecated
  private final String _robotId;

  private final int _index;
  private final BotAction _action;

  private final NodeStartListener.Actor _parentRef;
}
