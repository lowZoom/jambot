package luj.game.robot.internal.start.botinstance;

import luj.game.robot.internal.instance.action.step.ActionStep;

public class BotCurrentStep {

  public BotCurrentStep(ActionStep stepConf) {
    _stepConf = stepConf;
  }

  public int getWaitCount() {
    return _waitCount;
  }

  public void setWaitCount(int waitCount) {
    _waitCount = waitCount;
  }

  public ActionStep getStepConf() {
    return _stepConf;
  }

  private int _waitCount;

  private final ActionStep _stepConf;
}
