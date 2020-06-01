package luj.game.robot.internal.concurrent.instance.command;

import java.util.Map;

public class BotExecuteCommandMsg {

  public BotExecuteCommandMsg(Class<?> commandType, Map<String, Object> param) {
    _commandType = commandType;
    _param = param;
  }

  public Class<?> getCommandType() {
    return _commandType;
  }

  public Map<String, Object> getParam() {
    return _param;
  }

  private final Class<?> _commandType;

  private final Map<String, Object> _param;
}
