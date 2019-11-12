package luj.game.robot.internal.concurrent.instance.receive;

import java.util.Map;
import luj.ava.spring.Internal;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;
import luj.game.robot.internal.start.botinstance.RobotState;

@Internal
final class OnReceiveProto implements RobotInstanceActor.Handler<BotReceiveProtoMsg> {

  @Override
  public void onHandle(Context ctx) throws Exception {
    BotReceiveProtoMsg msg = ctx.getMessage(this);
    RobotInstanceActor actor = ctx.getActorState(this);

    RobotInstanceDependency instanceDep = actor.getDependency();
    RobotInstanceInjectRoot injectRoot = instanceDep.getInjectRoot();
    RobotProtoDecoder protoDecoder = injectRoot.getProtoDecoder();

    DecodeContextImpl decodeCtx = new DecodeContextImpl(msg.getProtoData());
    Object proto = protoDecoder.decode(decodeCtx);

    Map<Class<?>, RobotProtoHandler<?>> handlerMap = instanceDep.getProtoHandleMap();
    Class<?> protoType = proto.getClass();
    RobotProtoHandler<?> handler = handlerMap.get(protoType);

    if (handler == null) {
//      LOG.debug("未处理的协议包：{}", proto.getClass().getName());
      return;
    }

    RobotState robotState = actor.getRobotState();

    handler.onHandle(new HandlerContextImpl(proto, robotState,
        instanceDep.getLujnet(), injectRoot.getProtoEncoder(), ctx.getActorRef()));
  }

//  private static final Logger LOG = LoggerFactory.getLogger(OnReceiveProto.class);
}
