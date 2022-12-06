package luj.game.robot.internal.dynamic.init;

import java.util.List;
import luj.game.robot.api.action.RobotDataCommand;
import luj.game.robot.api.boot.RobotStartListener;

final class ResultImpl implements DynamicInitInvoker.Result {

  @Override
  public List<RobotStartListener> startListeners() {
    return _start;
  }

  @Override
  public List<RobotDataCommand<?>> commandList() {
    return _command;
  }

  List<RobotStartListener> _start;

  List<RobotDataCommand<?>> _command;
}
