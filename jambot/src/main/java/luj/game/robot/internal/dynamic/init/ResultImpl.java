package luj.game.robot.internal.dynamic.init;

import java.util.List;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.boot.RobotStartListener;

final class ResultImpl implements DynamicInitInvoker.Result {

  @Override
  public List<RobotStartListener> startListeners() {
    return _start;
  }

  @Override
  public List<RobotCommand<?>> commandList() {
    return _command;
  }

  List<RobotStartListener> _start;

  List<RobotCommand<?>> _command;
}
