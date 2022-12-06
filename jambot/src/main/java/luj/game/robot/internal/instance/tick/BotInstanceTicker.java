package luj.game.robot.internal.instance.tick;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableMap;
import com.typesafe.config.Config;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import luj.bean.api.BeanContext;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.concurrent.instance.command.BotExecuteCommandMsg;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;
import luj.game.robot.internal.instance.action.BotAction;
import luj.game.robot.internal.instance.action.step.ActionStep;
import luj.game.robot.internal.instance.action.step.StepType;
import luj.game.robot.internal.instance.action.step.arg.StepWaitArg;
import luj.game.robot.internal.instance.action.step.steps.StepCommand;
import luj.game.robot.internal.instance.config.StatusConf;
import luj.game.robot.internal.instance.tick.step.NextStepGetter;
import luj.game.robot.internal.instance.tick.step.NextStepGetterFactory;
import luj.game.robot.internal.instance.tick.step.param.StepParamResolverV2;
import luj.game.robot.internal.instance.tick.wait.WaitStepFinishTrier;
import luj.game.robot.internal.instance.tick.wait.WaitingProtoChecker;
import luj.game.robot.internal.start.botinstance.BotCurrentStep;
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
    if (handleWait()) {
      return;
    }

    String curStatusName = _botState.getStatus();
    StatusConf curStatus = _botState.getStatusMap().get(curStatusName);
    List<StatusConf.Action> actionList = curStatus.getActionList();
    if (actionList.isEmpty()) {
      LOG.warn("状态下没有动作：{}", curStatusName);
      return;
    }

    NextStepGetter nextStepGetter = new NextStepGetterFactory(_botState, actionList).create();
    NextStepGetter.Result next = nextStepGetter.getNext();

    StatusConf.Action nextAction = actionList.get(next.actionIndex());
    BotAction nextActionConf = nextAction.conf();
    ActionStep nextStepConf = nextActionConf.getStepList().get(next.stepIndex());

    _botState.setActionIndex(next.actionIndex());
    _botState.setStepIndex(next.stepIndex());
    _botState.setCurStep(new BotCurrentStep(nextStepConf));

    StepType type = nextStepConf.getType();
    LOG.debug("{} -> {}，状态({})", type, nextStepConf.getArg(), curStatusName);

    switch (type) {
      case COMMAND: {
        typeCommand((StepCommand) nextStepConf.getArg(), (Config) nextAction.args());
        return;
      }
      case WAIT: {
        handleWait();
//        return;
      }
    }
  }

  private boolean handleWait() {
    if (!new WaitingProtoChecker(_botState).isWaiting()) {
      return false;
    }

    if (tryFinishWait()) {
      return false;
    }

    BotCurrentStep curStep = _botState.getCurStep();
    ActionStep stepConf = curStep.getStepConf();
    StepWaitArg waitArg = (StepWaitArg) stepConf.getArg();

    OptionalInt maxWait = waitArg.maxWait();
    int oldWait = curStep.getWaitCount();
    if (maxWait.isPresent() && oldWait >= maxWait.getAsInt()) {
      return false;
    }

    int newWait = oldWait + 1;
    curStep.setWaitCount(newWait);

    LOG.debug("等待协议：{}，次数：{}", waitArg.protoType().getName(), newWait);
    return true;
  }

  private void typeCommand(StepCommand step, Config actionParam) {
    String cmdType = step.commandType();
    CommandMap.Command cmd = _instanceDep.getCommandMap().get(cmdType);
    checkNotNull(cmd, cmdType);

    Class<?> paramType = cmd.getParamType();
    BeanContext lujbean = _instanceDep.getLujbean();

    Map<String, Object> param = (paramType == Void.class) ? ImmutableMap.of() :
        new StepParamResolverV2(step.paramV2(), paramType, lujbean).resolve();
//        new StepParamResolver(step.param(), actionParam, paramType, lujbean).resolve();

    _instanceRef.tell(new BotExecuteCommandMsg(cmdType, param));
  }

  private boolean tryFinishWait() {
    return new WaitStepFinishTrier(_botState, _instanceRef).tryFinish();
  }

  private static final Logger LOG = LoggerFactory.getLogger(BotInstanceTicker.class);

  private final RobotState _botState;
  private final Tellable _instanceRef;

  private final RobotInstanceDependency _instanceDep;
}
