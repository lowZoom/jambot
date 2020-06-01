package luj.game.robot.internal.concurrent.instance.command.map;

import java.util.Map;

final class CommandMapImpl implements CommandMap {

  CommandMapImpl(Map<Class<?>, Command> cmdMap) {
    _cmdMap = cmdMap;
  }

  @Override
  public Command get(Class<?> commandType) {
    return _cmdMap.get(commandType);
  }

  private final Map<Class<?>, CommandMap.Command> _cmdMap;
}
