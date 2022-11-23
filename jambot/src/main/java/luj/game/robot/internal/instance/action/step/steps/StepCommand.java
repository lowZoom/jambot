package luj.game.robot.internal.instance.action.step.steps;

import com.typesafe.config.Config;
import java.util.Map;

public record StepCommand(
    String commandType,
    @Deprecated Config param,
    Map<String, Object> paramV2) {
  // NOOP
}
