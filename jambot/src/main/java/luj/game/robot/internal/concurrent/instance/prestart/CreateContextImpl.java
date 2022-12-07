package luj.game.robot.internal.concurrent.instance.prestart;

import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.internal.start.botinstance.RobotState;

final class CreateContextImpl implements RobotCreateListener.Context {

  @Override
  public void putData(Object data) {
    _robotState.getDataMap().put(data.getClass(), data);
  }

  @Override
  public RobotCreateListener.Internal getInternal() {
    return _internal;
  }

  RobotState _robotState;

  RobotCreateListener.Internal _internal;
}
