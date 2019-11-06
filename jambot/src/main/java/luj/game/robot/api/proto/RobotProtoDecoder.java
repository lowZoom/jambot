package luj.game.robot.api.proto;

import io.netty.buffer.ByteBuf;

public interface RobotProtoDecoder {

  interface Context {

    ByteBuf data();
  }

  Object decode(Context ctx) throws Exception;
}
