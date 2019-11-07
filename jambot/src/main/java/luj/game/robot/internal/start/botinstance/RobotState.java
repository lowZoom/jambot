package luj.game.robot.internal.start.botinstance;

import akka.actor.ActorRef;
import java.util.Map;
import luj.net.api.NetConnection;

public class RobotState {

  public ActorRef getInstanceRef() {
    return _instanceRef;
  }

  public void setInstanceRef(ActorRef instanceRef) {
    _instanceRef = instanceRef;
  }

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

  private ActorRef _instanceRef;
  private NetConnection _connection;

  private Map<Class<?>, Object> _dataMap;
}
