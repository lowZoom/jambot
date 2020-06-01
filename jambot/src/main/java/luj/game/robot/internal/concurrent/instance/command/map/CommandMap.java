package luj.game.robot.internal.concurrent.instance.command.map;

import luj.game.robot.api.action.RobotCommand;

public interface CommandMap {

  interface Command {

    RobotCommand<?> getInstance();

    Class<?> getCommandType();

    Class<?> getParamType();
  }

  Command get(Class<?> commandType);
}
