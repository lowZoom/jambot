package luj.game.robot.internal.instance.action.step.steps;

import com.typesafe.config.Config;

public record StepCommand(
    String commandType,
    Config param) {
  // NOOP
}
