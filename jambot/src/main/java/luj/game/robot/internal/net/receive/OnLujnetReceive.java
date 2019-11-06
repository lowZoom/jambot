package luj.game.robot.internal.net.receive;

import java.util.Map;
import luj.ava.spring.Internal;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.net.BotbeanInLujnet;
import luj.game.robot.internal.start.botinstance.RobotState;
import luj.net.api.NetConnection;
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

    if (handler == null) {
//      LOG.debug("未处理的协议包：{}", proto.getClass().getName());
      return;
    }

    NetConnection conn = ctx.getConnection();
    RobotState robotState = conn.getApplicationParam();

    handler.onHandle(new HandlerContextImpl(proto, robotState,
        conn.getContext(), param.getProtoEncoder()));
  }

//  private static final Logger LOG = LoggerFactory.getLogger(OnLujnetReceive.class);
}
