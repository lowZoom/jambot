package luj.game.robot.internal.dynamic.init;

import java.util.List;
import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.api.action.RobotDataCommand;
import luj.game.robot.api.boot.RobotStartListener;

final class ResultImpl implements DynamicInitInvoker.Result {

  @Override
  public List<RobotStartListener> startListener() {
    return _start;
  }

  @Override
  public List<RobotCreateListener> createListener() {
    return _create;
  }

  @Override
  public List<RobotDataCommand<?>> dataCommand() {
    return _command;
  }

  List<RobotStartListener> _start;

  List<RobotCreateListener> _create;
  List<RobotDataCommand<?>> _command;
}
