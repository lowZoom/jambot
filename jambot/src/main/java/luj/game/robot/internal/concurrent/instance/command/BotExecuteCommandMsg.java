package luj.game.robot.internal.concurrent.instance.command;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

public record BotExecuteCommandMsg(
    String commandType,
    Map<String, Object> param) {

  public BotExecuteCommandMsg {
    checkNotNull(param);
  }
}
