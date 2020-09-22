package luj.game.robot.internal.instance.action.step.arg;

import java.util.Optional;
import java.util.OptionalInt;

public interface StepWaitArg {

  Class<?> protoType();

  OptionalInt maxWait();

  Optional<String> nextStatus(Object actionArg);
}
