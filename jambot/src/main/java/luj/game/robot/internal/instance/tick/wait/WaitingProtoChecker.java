package luj.game.robot.internal.instance.tick.wait;

import luj.game.robot.internal.instance.action.step.StepType;
import luj.game.robot.internal.start.botinstance.BotCurrentStep;
import luj.game.robot.internal.start.botinstance.RobotState;

public class WaitingProtoChecker {

  public WaitingProtoChecker(RobotState botState) {
    _botState = botState;
  }

  public boolean isWaiting() {
    BotCurrentStep curStep = _botState.getCurStep();
    return curStep != null && curStep.getStepConf().getType() == StepType.WAIT;
  }

  private final RobotState _botState;
}
