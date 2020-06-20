package luj.game.robot.internal.instance.config;

import java.util.Map;

public class BotConf {

  public BotConf(String name, int amount, String initStatus, Map<String, StatusConf> statusMap) {
    _name = name;
    _amount = amount;
    _initStatus = initStatus;
    _statusMap = statusMap;
  }

  public String getName() {
    return _name;
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

  private final String _name;
  private final int _amount;

  private final String _initStatus;
  private final Map<String, StatusConf> _statusMap;
}
