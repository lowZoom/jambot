package luj.game.robot.internal.instance.action;

import java.util.List;
import luj.game.robot.internal.instance.action.step.ActionStep;

public class BotAction {

  public BotAction(String name, List<ActionStep> stepList) {
    _name = name;
    _stepList = stepList;
  }

  public String getName() {
    return _name;
  }

  public List<ActionStep> getStepList() {
    return _stepList;
  }

  private final String _name;

  private final List<ActionStep> _stepList;
}
