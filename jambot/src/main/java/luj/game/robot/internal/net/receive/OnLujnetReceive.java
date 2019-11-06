package luj.game.robot.internal.net.receive;

import java.util.Map;
import luj.ava.spring.Internal;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.net.BotbeanInLujnet;
import luj.net.api.data.NetReceiveListener;

@Internal
final class OnLujnetReceive implements NetReceiveListener {

  @Override
  public void onReceive(Context ctx) throws Exception {
    BotbeanInLujnet param = ctx.getApplicationParam();
    RobotProtoDecoder protoDecoder = param.getProtoDecoder();

    DecodeContextImpl decodeCtx = new DecodeContextImpl(ctx.getData());
    Object proto = protoDecoder.decode(decodeCtx);

    Map<Class<?>, RobotProtoHandler<?>> handlerMap = param.getHandlerMap();
    RobotProtoHandler<?> handler = handlerMap.get(proto.getClass());
    System.out.println(handler.getClass());

    HandlerContextImpl handleCtx = new HandlerContextImpl(proto);
    handler.onHandle(handleCtx);
  }
}
