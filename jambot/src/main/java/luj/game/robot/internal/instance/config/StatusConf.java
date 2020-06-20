package luj.game.robot.internal.instance.config;

import java.util.List;
import luj.game.robot.internal.instance.action.BotAction;

public class StatusConf {

  public StatusConf(String key, List<BotAction> actionList) {
    _key = key;
    _actionList = actionList;
  }

  public String getKey() {
    return _key;
  }

  private final String _key;

  private final List<BotAction> _actionList;
}
