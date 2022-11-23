package luj.game.robot.internal.concurrent.instance.command.map;

import java.util.Map;

final class CommandMapImpl implements CommandMap {

  @Override
  public Command get(String commandType) {
    return _cmdMap.get(commandType);
  }

  Map<String, CommandMap.Command> _cmdMap;
}
