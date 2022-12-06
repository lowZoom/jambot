package luj.game.robot.internal.concurrent.instance.command;

import java.util.Map;

public record BotExecuteCommandMsg(
    String commandType,
    Map<String, Object> param) {
  // NOOP
}
