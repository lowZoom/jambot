package luj.game.robot.internal.start.botinstance;

import luj.net.api.NetConnection;

public class RobotState {

  public NetConnection getConnection() {
    return _connection;
  }

  public void setConnection(NetConnection connection) {
    _connection = connection;
  }

  private NetConnection _connection;
}
