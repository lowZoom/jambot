package luj.game.robot.internal.instance.tick.step;

import luj.game.robot.internal.instance.action.BotAction;

final class ActionImpl implements NextStepGetter.Action {

  ActionImpl(BotAction action) {
    _action = action;
  }

  @Override
  public int getStepCount() {
    return _action.getStepList().size();
  }

  private final BotAction _action;
}
