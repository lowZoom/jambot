package luj.game.robot.internal.instance.tick.wait;

import static com.google.common.base.Preconditions.checkState;

import java.util.Queue;
import luj.game.robot.internal.instance.action.step.ActionStep;
import luj.game.robot.internal.instance.action.step.StepType;
import luj.game.robot.internal.start.botinstance.RobotState;

public class WaitStepFinishTrier {

  public WaitStepFinishTrier(RobotState botState) {
    _botState = botState;
  }

  public boolean tryFinish() {
    ActionStep curStep = _botState.getCurStep();
    StepType stepType = curStep.getType();
    checkState(stepType == StepType.WAIT, stepType);

    Class<?> protoType = (Class<?>) curStep.getArg();
    Queue<Class<?>> receiveHistory = _botState.getReceiveHistory();

    while (receiveHistory.poll() != protoType) {
      if (receiveHistory.isEmpty()) {
        return false;
      }
    }

    _botState.setCurStep(null);
    return true;
  }

  private final RobotState _botState;
}
