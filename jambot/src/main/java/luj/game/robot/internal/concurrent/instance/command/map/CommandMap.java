package luj.game.robot.internal.concurrent.instance.command.map;

import luj.game.robot.api.action.RobotCommand;
import org.slf4j.Logger;

public interface CommandMap {

  interface Command {

    RobotCommand<?> getInstance();

    Class<?> getCommandType();

    Class<?> getParamType();

    Logger getLogger();
  }

  Command get(Class<?> commandType);
}
