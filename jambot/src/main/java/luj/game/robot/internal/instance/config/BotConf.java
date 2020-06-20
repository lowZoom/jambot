package luj.game.robot.internal.instance.config;

import java.util.Map;

public class BotConf {

  public BotConf(int amount, String initStatus, Map<String, StatusConf> statusMap) {
    _amount = amount;
    _initStatus = initStatus;
    _statusMap = statusMap;
  }

  public int getAmount() {
    return _amount;
  }

  public String getInitStatus() {
    return _initStatus;
  }

  public Map<String, StatusConf> getStatusMap() {
    return _statusMap;
  }

  private final int _amount;

  private final String _initStatus;
  private final Map<String, StatusConf> _statusMap;
}
