package luj.game.robot.internal.instance.action;

import java.util.List;
import luj.game.robot.internal.instance.action.step.ActionStep;
import luj.game.robot.internal.instance.action.step.StepType;

public class BotAction {

  public BotAction(String name, StepType stepType, List<ActionStep> stepList) {
    _name = name;
    _stepType = stepType;
    _stepList = stepList;
  }

  public String getName() {
    return _name;
  }

  public List<ActionStep> getStepList() {
    return _stepList;
  }

  private final String _name;

  private final StepType _stepType;
  private final List<ActionStep> _stepList;
}
