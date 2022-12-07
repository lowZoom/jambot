package luj.game.robot.internal.instance.action.step.steps;

import java.util.Map;

public record StepCommand(
    String commandType,
    Map<String, Object> param) {
  // NOOP
}
