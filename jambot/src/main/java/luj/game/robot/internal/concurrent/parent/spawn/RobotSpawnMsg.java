package luj.game.robot.internal.concurrent.parent.spawn;

import java.util.Map;
import luj.game.robot.internal.instance.config.StatusConf;

public class RobotSpawnMsg {

  public RobotSpawnMsg(String robotId, String initStatus, Map<String, StatusConf> statusMap) {
    _robotId = robotId;
    _initStatus = initStatus;
    _statusMap = statusMap;
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

  @Deprecated
  private final String _robotId;

  private final String _initStatus;
  private final Map<String, StatusConf> _statusMap;
}
