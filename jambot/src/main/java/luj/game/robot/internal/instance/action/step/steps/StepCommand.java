package luj.game.robot.internal.instance.action.step.steps;

import com.typesafe.config.Config;

public class StepCommand {

  public StepCommand(Class<?> commandType, Config param) {
    _commandType = commandType;
    _param = param;
  }

  public Class<?> getCommandType() {
    return _commandType;
  }

  public Config getParam() {
    return _param;
  }

  @Override
  public String toString() {
    return _commandType.getSimpleName() + _param;
  }

  private final Class<?> _commandType;

  private final Config _param;
}
