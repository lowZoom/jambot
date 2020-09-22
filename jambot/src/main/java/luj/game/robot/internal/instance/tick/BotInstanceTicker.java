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
import luj.game.robot.internal.instance.config.StatusConf;
import luj.game.robot.internal.instance.tick.step.NextStepGetter;
import luj.game.robot.internal.instance.tick.step.NextStepGetterFactory;
import luj.game.robot.internal.instance.tick.wait.WaitStepFinishTrier;
import luj.game.robot.internal.instance.tick.wait.WaitingProtoChecker;
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
    if (new WaitingProtoChecker(_botState).isWaiting() && !tryFinishWait()) {
      LOG.debug("等待协议：{}", _botState.getCurStep().getArg());
      return;
    }

    String curStatusName = _botState.getStatus();
    StatusConf curStatus = _botState.getStatusMap().get(curStatusName);
    List<StatusConf.Action> actionList = curStatus.getActionList();
    if (actionList.isEmpty()) {
      return;
    }

    NextStepGetter nextStepGetter = new NextStepGetterFactory(_botState, actionList).create();
    NextStepGetter.Result next = nextStepGetter.getNext();

    BotAction curAction = actionList.get(next.actionIndex()).conf();
    ActionStep nextStep = curAction.getStepList().get(next.stepIndex());

    _botState.setActionIndex(next.actionIndex());
    _botState.setStepIndex(next.stepIndex());
    _botState.setCurStep(nextStep);

    StepType type = nextStep.getType();
    LOG.debug("{} -> {}", type, nextStep.getArg());

    switch (type) {
      case COMMAND: {
        typeCommand((StepCommand) nextStep.getArg());
        return;
      }
      case WAIT: {
        tryFinishWait();
        return;
      }
    }
  }

  private void typeCommand(StepCommand arg) {
    Class<?> cmdType = arg.getCommandType();
    CommandMap.Command cmd = _instanceDep.getCommandMap().get(cmdType);
    checkNotNull(cmd, cmdType.getName());

    Class<?> paramType = cmd.getParamType();
    Object param = (paramType == Void.class) ? null :
        _instanceDep.getLujbean().create(paramType, arg.getParam());

    _instanceRef.tell(new BotExecuteCommandMsg(cmdType, param));
  }

  private boolean tryFinishWait() {
    return new WaitStepFinishTrier(_botState, _instanceRef, _instanceDep.getLujbean()).tryFinish();
  }

  private static final Logger LOG = LoggerFactory.getLogger(BotInstanceTicker.class);

  private final RobotState _botState;
  private final Tellable _instanceRef;

  private final RobotInstanceDependency _instanceDep;
}
