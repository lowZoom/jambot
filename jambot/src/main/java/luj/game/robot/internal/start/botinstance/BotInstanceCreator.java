package luj.game.robot.internal.start.botinstance;

import java.util.Map;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.concurrent.parent.spawn.RobotSpawnMsg;
import luj.game.robot.internal.instance.config.StatusConf;

public class BotInstanceCreator {

  public BotInstanceCreator(String robotId, String initStatus,
      Map<String, StatusConf> statusMap, Tellable parentRef) {
    _robotId = robotId;
    _initStatus = initStatus;
    _statusMap = statusMap;
    _parentRef = parentRef;
  }

  public void create() {
    RobotSpawnMsg msg = new RobotSpawnMsg(_robotId, _initStatus, _statusMap);
    _parentRef.tell(msg);
  }

  @Deprecated
  private final String _robotId;

  private final String _initStatus;
  private final Map<String, StatusConf> _statusMap;

  private final Tellable _parentRef;
}
