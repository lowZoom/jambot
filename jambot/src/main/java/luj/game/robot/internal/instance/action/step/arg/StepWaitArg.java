package luj.game.robot.internal.instance.action.step.arg;

import java.util.Optional;

public interface StepWaitArg {

  Class<?> protoType();

  Optional<String> nextStatus(Object actionArg);
}
