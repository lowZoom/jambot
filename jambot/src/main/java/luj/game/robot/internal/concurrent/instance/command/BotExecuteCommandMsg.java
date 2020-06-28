package luj.game.robot.internal.concurrent.instance.command;

public class BotExecuteCommandMsg {

  public BotExecuteCommandMsg(Class<?> commandType, Object param) {
    _commandType = commandType;
    _param = param;
  }

  public Class<?> getCommandType() {
    return _commandType;
  }

  public Object getParam() {
    return _param;
  }

  private final Class<?> _commandType;

  private final Object _param;
}
