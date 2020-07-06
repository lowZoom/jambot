package luj.game.robot.internal.instance.tick.step;

import static java.util.stream.Collectors.toList;

import java.util.List;
import luj.game.robot.internal.instance.action.BotAction;
import luj.game.robot.internal.start.botinstance.RobotState;

public class NextStepGetterFactory {

  public NextStepGetterFactory(RobotState botState, List<BotAction> actionList) {
    _botState = botState;
    _actionList = actionList;
  }

  public NextStepGetter create() {
    return new NextStepGetter(_actionList.stream()
        .map(ActionImpl::new)
        .collect(toList()), _botState.getActionIndex(), _botState.getStepIndex());
  }

  private final RobotState _botState;

  private final List<BotAction> _actionList;
}
