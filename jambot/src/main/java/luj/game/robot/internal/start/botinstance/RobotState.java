package luj.game.robot.internal.start.botinstance;

import java.util.Map;
import luj.game.robot.internal.instance.config.StatusConf;
import luj.net.api.client.NetConnection;

public class RobotState {

  public NetConnection getConnection() {
    return _connection;
  }

  public void setConnection(NetConnection connection) {
    _connection = connection;
  }

  public Map<Class<?>, Object> getDataMap() {
    return _dataMap;
  }

  public void setDataMap(Map<Class<?>, Object> dataMap) {
    _dataMap = dataMap;
  }

  public String getStatus() {
    return _status;
  }

  public void setStatus(String status) {
    _status = status;
  }

  public Map<String, StatusConf> getStatusMap() {
    return _statusMap;
  }

  public void setStatusMap(Map<String, StatusConf> statusMap) {
    _statusMap = statusMap;
  }

  public void setStepIndex(int stepIndex) {
    _stepIndex = stepIndex;
  }

  public int getStepIndex() {
    return _stepIndex;
  }

  public int getActionIndex() {
    return _actionIndex;
  }

  public void setActionIndex(int actionIndex) {
    _actionIndex = actionIndex;
  }

  private NetConnection _connection;
  private Map<Class<?>, Object> _dataMap;

  private String _status;
  private Map<String, StatusConf> _statusMap;

  private int _actionIndex;
  private int _stepIndex;
}
