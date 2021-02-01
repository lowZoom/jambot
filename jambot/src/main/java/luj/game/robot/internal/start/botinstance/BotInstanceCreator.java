package luj.game.robot.internal.start.botinstance;

import java.util.Map;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.concurrent.parent.spawn.RobotSpawnMsg;
import luj.game.robot.internal.instance.config.StatusConf;

public class BotInstanceCreator {

  public BotInstanceCreator(String initStatus, Map<String, StatusConf> statusMap,
      double tickSecMin, double tickSecMax, Tellable parentRef) {
    _initStatus = initStatus;
    _statusMap = statusMap;
    _tickSecMin = tickSecMin;
    _tickSecMax = tickSecMax;
    _parentRef = parentRef;
  }

  public void create() {
    int tickMin = secToMs(_tickSecMin);
    int tickMax = secToMs(_tickSecMax);

    RobotSpawnMsg msg = new RobotSpawnMsg(_initStatus, _statusMap, tickMin, tickMax);
    _parentRef.tell(msg);
  }

  private int secToMs(double sec) {
    return (int) Math.round(sec * 1000);
  }

  private final String _initStatus;
  private final Map<String, StatusConf> _statusMap;

  private final double _tickSecMin;
  private final double _tickSecMax;

  private final Tellable _parentRef;
}
