package luj.game.robot.internal.admin.actor;

public class AdminBotInfo {

  public AdminBotInfo(int index) {
    _index = index;
  }

  public boolean isConnected() {
    return _connected;
  }

  public void setConnected(boolean connected) {
    _connected = connected;
  }

  public String getStatus() {
    return _status;
  }

  public void setStatus(String status) {
    _status = status;
  }

  public int getIndex() {
    return _index;
  }

  private boolean _connected;
  private String _status;

  private final int _index;
}
