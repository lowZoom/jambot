package luj.game.robot.internal.instance.tick;

import java.util.List;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.internal.concurrent.instance.command.BotExecuteCommandMsg;
import luj.game.robot.internal.instance.action.step.ActionStep;
import luj.game.robot.internal.instance.action.step.StepType;
import luj.game.robot.internal.instance.action.step.steps.StepCommand;
import luj.game.robot.internal.start.botinstance.RobotState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotInstanceTicker {

  public BotInstanceTicker(RobotState botState, ActorMessageHandler.Ref instanceRef) {
    _botState = botState;
    _instanceRef = instanceRef;
  }

  public void tick() {
    ActionStep nextStep = getNextStep();
    StepType type = nextStep.getType();
    LOG.debug("{}", type);

    switch (type) {
      case COMMAND: {
        typeCommand((StepCommand) nextStep.getArg());
        return;
      }
    }
  }

  private ActionStep getNextStep() {
    int oldIndex = _botState.getStepIndex();
    int nextIndex = oldIndex + 1;

    List<ActionStep> stepList = _botState.getCurAction().getStepList();
    if (nextIndex >= stepList.size()) {
      return stepList.get(oldIndex);
    }

    _botState.setStepIndex(nextIndex);
    return stepList.get(nextIndex);
  }

  private void typeCommand(StepCommand arg) {
    _instanceRef.tell(new BotExecuteCommandMsg(arg.getCommandType(), arg.getParam()));
  }

  private static final Logger LOG = LoggerFactory.getLogger(BotInstanceTicker.class);

  private final RobotState _botState;
  private final ActorMessageHandler.Ref _instanceRef;
}
