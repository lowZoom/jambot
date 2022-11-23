package luj.game.robot.internal.instance.config;

import java.util.Map;

public class BotConf {

  public BotConf(String name, int amount, double tickSecMin, double tickSecMax,
      Map<String, StatusConf> statusMap, String initStatus) {
    _name = name;
    _amount = amount;
    _tickSecMin = tickSecMin;
    _tickSecMax = tickSecMax;
    _statusMap = statusMap;
    _initStatus = initStatus;
  }

  public String getName() {
    return _name;
  }

  public int getAmount() {
    return _amount;
  }

  @Deprecated
  public double getTickSecMin() {
    return _tickSecMin;
  }

  @Deprecated
  public double getTickSecMax() {
    return _tickSecMax;
  }

  public String getInitStatus() {
    return _initStatus;
  }

  public Map<String, StatusConf> getStatusMap() {
    return _statusMap;
  }

  private final String _name;
  private final int _amount;

  private final double _tickSecMin;
  private final double _tickSecMax;

  private final Map<String, StatusConf> _statusMap;
  private final String _initStatus;
}
