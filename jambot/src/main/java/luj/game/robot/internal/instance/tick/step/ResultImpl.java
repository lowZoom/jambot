package luj.game.robot.internal.instance.tick.step;

final class ResultImpl implements NextStepGetter.Result {

  @Override
  public int actionIndex() {
    return _actionIndex;
  }

  @Override
  public int stepIndex() {
    return _stepIndex;
  }

  int _actionIndex;
  int _stepIndex;
}
