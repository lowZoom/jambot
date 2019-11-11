package luj.game.robot.api.proto;

public interface RobotProtoDecoder {

  interface Context {

    byte[] data();
  }

  Object decode(Context ctx) throws Exception;
}
