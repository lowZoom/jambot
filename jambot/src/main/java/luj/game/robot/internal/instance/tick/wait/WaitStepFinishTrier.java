package luj.game.robot.internal.instance.tick.wait;

import static com.google.common.base.Preconditions.checkState;

import java.util.Queue;
import luj.bean.api.BeanContext;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.concurrent.instance.status.ChangeStatusRequestor;
import luj.game.robot.internal.instance.action.step.ActionStep;
import luj.game.robot.internal.instance.action.step.StepType;
import luj.game.robot.internal.instance.action.step.arg.StepWaitArg;
import luj.game.robot.internal.instance.config.StatusConf;
import luj.game.robot.internal.start.botinstance.RobotState;

public class WaitStepFinishTrier {

  public WaitStepFinishTrier(RobotState botState, Tellable botRef, BeanContext lujbean) {
    _botState = botState;
    _botRef = botRef;
    _lujbean = lujbean;
  }

  public boolean tryFinish() {
    ActionStep curStep = _botState.getCurStep().getStepConf();
    StepType stepType = curStep.getType();
    checkState(stepType == StepType.WAIT, stepType);

    StepWaitArg waitArg = (StepWaitArg) curStep.getArg();
    Class<?> protoType = waitArg.protoType();
    Queue<Class<?>> receiveHistory = _botState.getReceiveHistory();

    while (receiveHistory.poll() != protoType) {
      if (receiveHistory.isEmpty()) {
        return false;
      }
    }

    _botState.setCurStep(null);

    String curStatusName = _botState.getStatus();
    StatusConf curStatus = _botState.getStatusMap().get(curStatusName);
    waitArg.nextStatus(curStatus.getActionList().get(_botState.getActionIndex()).args())
        .ifPresent(s -> new ChangeStatusRequestor(_botRef, s, _lujbean).request());

    return true;
  }

  private final RobotState _botState;

  private final Tellable _botRef;
  private final BeanContext _lujbean;
}
