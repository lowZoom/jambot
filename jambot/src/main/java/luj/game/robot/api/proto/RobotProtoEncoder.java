package luj.game.robot.api.proto;

public interface RobotProtoEncoder {

  interface Context {

    <T> T proto();
  }

  byte[] encode(Context ctx);
}
