package luj.game.robot.internal.start.botinstance;

import java.util.Map;
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

  private NetConnection _connection;

  private Map<Class<?>, Object> _dataMap;
}
