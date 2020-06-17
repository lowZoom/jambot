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

  public int getIndex() {
    return _index;
  }

  private boolean _connected;

  private final int _index;
}
