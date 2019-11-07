package luj.game.robot.internal.start.botinstance;

import luj.cluster.api.node.NodeStartListener;
import luj.game.robot.internal.concurrent.parent.spawn.RobotSpawnMsg;

public class BotInstanceCreator {

  public BotInstanceCreator(String robotId, NodeStartListener.Actor parentRef) {
    _robotId = robotId;
    _parentRef = parentRef;
  }

  public void create() {
    RobotSpawnMsg msg = new RobotSpawnMsg(_robotId);
    _parentRef.tell(msg);
  }

  private final String _robotId;

  private final NodeStartListener.Actor _parentRef;
}
