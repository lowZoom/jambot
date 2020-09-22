package luj.game.robot.internal.instance.config;

import java.util.List;
import luj.game.robot.internal.instance.action.BotAction;

public class StatusConf {

  public interface Action {

    BotAction conf();

    Object args();
  }

  public StatusConf(String key, List<Action> actionList) {
    _key = key;
    _actionList = actionList;
  }

  public String getKey() {
    return _key;
  }

  public List<Action> getActionList() {
    return _actionList;
  }

  private final String _key;

  private final List<Action> _actionList;
}
