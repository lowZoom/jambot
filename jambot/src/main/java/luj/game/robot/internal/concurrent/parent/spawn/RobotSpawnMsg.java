package luj.game.robot.internal.concurrent.parent.spawn;

import java.util.Map;
import luj.game.robot.internal.instance.config.StatusConf;

public class RobotSpawnMsg {

  public RobotSpawnMsg(String robotId, String initStatus,
      Map<String, StatusConf> statusMap, int tickMsMin, int tickMsMax) {
    _robotId = robotId;
    _initStatus = initStatus;
    _statusMap = statusMap;
    _tickMsMin = tickMsMin;
    _tickMsMax = tickMsMax;
  }

  @Deprecated
  public String getRobotId() {
    return _robotId;
  }

  public String getInitStatus() {
    return _initStatus;
  }

  public Map<String, StatusConf> getStatusMap() {
    return _statusMap;
  }

  public int getTickMsMin() {
    return _tickMsMin;
  }

  public int getTickMsMax() {
    return _tickMsMax;
  }

  @Deprecated
  private final String _robotId;

  private final String _initStatus;
  private final Map<String, StatusConf> _statusMap;

  private final int _tickMsMin;
  private final int _tickMsMax;
}
