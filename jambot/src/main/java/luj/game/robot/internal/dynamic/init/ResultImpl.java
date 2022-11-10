package luj.game.robot.internal.dynamic.init;

import java.util.List;
import luj.game.robot.api.boot.RobotStartListener;

final class ResultImpl implements DynamicInitInvoker.Result {

  @Override
  public List<RobotStartListener> getStartListeners() {
    return _start;
  }

  List<RobotStartListener> _start;
}
