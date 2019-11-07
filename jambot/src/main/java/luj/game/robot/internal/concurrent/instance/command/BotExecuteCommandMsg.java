package luj.game.robot.internal.concurrent.instance.command;

public class BotExecuteCommandMsg {

  public BotExecuteCommandMsg(Class<?> commandType) {
    _commandType = commandType;
  }

  public Class<?> getCommandType() {
    return _commandType;
  }

  private final Class<?> _commandType;
}
