package luj.game.robot.internal.concurrent.instance.command;

public record BotExecuteCommandMsg(
    String commandType,
    Object param) {
  // NOOP
}
