package luj.game.robot.internal.concurrent.instance.command.map;

import luj.game.robot.api.action.RobotDataCommand;
import org.slf4j.Logger;

public interface CommandMap {

  interface Command {

    RobotDataCommand<?> getInstance();

    Class<?> getCommandType();

    Class<?> getParamType();

    Logger getLogger();
  }

  Command get(String commandType);
}
