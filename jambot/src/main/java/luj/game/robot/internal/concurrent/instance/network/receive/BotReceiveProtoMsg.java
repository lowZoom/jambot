package luj.game.robot.internal.concurrent.instance.network.receive;

public record BotReceiveProtoMsg(
    String protoKey,
    Object protoObj,
    Object param) {
  // NOOP
}
