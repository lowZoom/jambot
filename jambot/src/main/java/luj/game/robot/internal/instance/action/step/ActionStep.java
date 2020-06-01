package luj.game.robot.internal.instance.action.step;

public class ActionStep {

  public ActionStep(StepType type, Object arg) {
    _type = type;
    _arg = arg;
  }

  public StepType getType() {
    return _type;
  }

  public Object getArg() {
    return _arg;
  }

  private final StepType _type;

  private final Object _arg;
}
