package luj.game.robot.internal.instance.action.step.steps;

import java.util.Map;

public class StepCommand {

  public StepCommand(Class<?> commandType, Map<String, Object> param) {
    _commandType = commandType;
    _param = param;
  }

  public Class<?> getCommandType() {
    return _commandType;
  }

  public Map<String, Object> getParam() {
    return _param;
  }

  @Override
  public String toString() {
    return _commandType.getSimpleName() + _param;
  }

  private final Class<?> _commandType;

  private final Map<String, Object> _param;
}
