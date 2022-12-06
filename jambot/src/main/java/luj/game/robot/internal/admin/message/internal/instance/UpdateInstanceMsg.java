package luj.game.robot.internal.admin.message.internal.instance;

public record UpdateInstanceMsg(
    Integer index,
    boolean isConnected,
    String status) {
  // NOOP
}
