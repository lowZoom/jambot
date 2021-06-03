package luj.game.robot.internal.instance.status;

import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.concurrent.instance.admin.UpdateAdminMsg;
import luj.game.robot.internal.concurrent.instance.tick.once.BotTickMsg;
import luj.game.robot.internal.start.botinstance.RobotState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotStatusChanger {

  public BotStatusChanger(RobotState instanceState, String newStatus, Tellable instanceRef) {
    _instanceState = instanceState;
    _newStatus = newStatus;
    _instanceRef = instanceRef;
  }

  public void change() {
    LOG.debug("状态切换：{} -> {}", _instanceState.getStatus(), _newStatus);

    _instanceState.setStatus(_newStatus);
    _instanceState.setActionIndex(0);
    _instanceState.setStepIndex(-1);
    _instanceState.setCurStep(null);

    _instanceRef.tell(UpdateAdminMsg.INSTANCE);
    _instanceRef.tell(BotTickMsg.INSTANCE);
  }

  private static final Logger LOG = LoggerFactory.getLogger(BotStatusChanger.class);

  private final RobotState _instanceState;

  private final String _newStatus;
  private final Tellable _instanceRef;
}
