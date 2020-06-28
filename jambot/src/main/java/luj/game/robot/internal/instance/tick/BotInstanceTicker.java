package luj.game.robot.internal.instance.tick;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.concurrent.instance.command.BotExecuteCommandMsg;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;
import luj.game.robot.internal.instance.action.BotAction;
import luj.game.robot.internal.instance.action.step.ActionStep;
import luj.game.robot.internal.instance.action.step.StepType;
import luj.game.robot.internal.instance.action.step.steps.StepCommand;
import luj.game.robot.internal.start.botinstance.RobotState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotInstanceTicker {

  public BotInstanceTicker(RobotState botState, Tellable instanceRef,
      RobotInstanceDependency instanceDep) {
    _botState = botState;
    _instanceRef = instanceRef;
    _instanceDep = instanceDep;
  }

  public void tick() {
    String curStatus = _botState.getStatus();
    List<BotAction> actionList = _botState.getStatusMap().get(curStatus).getActionList();
    if (actionList.isEmpty()) {
      return;
    }

    BotAction curAction = actionList.get(_botState.getActionIndex());
    ActionStep nextStep = getNextStep(curAction);
    StepType type = nextStep.getType();
    LOG.debug("{}", type);

    switch (type) {
      case COMMAND: {
        typeCommand((StepCommand) nextStep.getArg());
        return;
      }
    }
  }

  private ActionStep getNextStep(BotAction curAction) {
    int oldIndex = _botState.getStepIndex();
    int nextIndex = oldIndex + 1;

    List<ActionStep> stepList = curAction.getStepList();
    if (nextIndex >= stepList.size()) {
      return stepList.get(oldIndex);
    }

    _botState.setStepIndex(nextIndex);
    return stepList.get(nextIndex);
  }

  private void typeCommand(StepCommand arg) {
    Class<?> cmdType = arg.getCommandType();
    CommandMap.Command cmd = _instanceDep.getCommandMap().get(cmdType);
    checkNotNull(cmd, cmdType.getName());

    Object param = _instanceDep.getLujbean().create(cmd.getParamType(), arg.getParam());
    _instanceRef.tell(new BotExecuteCommandMsg(cmdType, param));
  }

  private static final Logger LOG = LoggerFactory.getLogger(BotInstanceTicker.class);

  private final RobotState _botState;
  private final Tellable _instanceRef;

  private final RobotInstanceDependency _instanceDep;
}
