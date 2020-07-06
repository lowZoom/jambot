package luj.game.robot.internal.instance.tick.step;

import java.util.List;

public class NextStepGetter {

  public interface Action {

    int getStepCount();
  }

  public interface Result {

    int actionIndex();

    int stepIndex();
  }

  public NextStepGetter(List<Action> actionList, int oldActionIndex, int oldStepIndex) {
    _actionList = actionList;
    _oldActionIndex = oldActionIndex;
    _oldStepIndex = oldStepIndex;
  }

  public Result getNext() {
    Action oldAction = _actionList.get(_oldActionIndex);
    int newActionIndex = getNewActionIndex(oldAction);

    ResultImpl result = new ResultImpl();
    result._actionIndex = newActionIndex;
    result._stepIndex = newActionIndex != _oldActionIndex ? 0 : _oldStepIndex + 1;
    return result;
  }

  private int getNewActionIndex(Action oldAction) {
    if (_oldStepIndex + 1 < oldAction.getStepCount()) {
      return _oldActionIndex;
    }
    return (_oldActionIndex + 1) % _actionList.size();
  }

  private final List<Action> _actionList;

  private final int _oldActionIndex;
  private final int _oldStepIndex;
}
