package luj.game.robot.internal.concurrent.instance.network.receive;

import java.util.Map;
import luj.ava.spring.Internal;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;

@Internal
final class OnReceiveProto implements RobotInstanceActor.Handler<BotReceiveProtoMsg> {

  @Override
  public void onHandle(Context ctx) throws Exception {
    RobotInstanceActor self = ctx.getActorState(this);
    BotReceiveProtoMsg msg = ctx.getMessage(this);

    RobotInstanceDependency instanceDep = self.getDependency();
    RobotInstanceInjectRoot injectRoot = instanceDep.getInjectRoot();
    RobotProtoDecoder protoDecoder = injectRoot.getProtoDecoder();

    DecodeContextImpl decodeCtx = new DecodeContextImpl(msg.getProtoData());
    Object proto = protoDecoder.decode(decodeCtx);

    Map<Class<?>, RobotProtoHandler<?>> handlerMap = instanceDep.getProtoHandleMap();
    Class<?> protoType = proto.getClass();
    RobotProtoHandler<?> handler = handlerMap.get(protoType);

    if (handler == null) {
//      LOG.debug("未处理的协议包：{}，{}", proto.getClass().getName(), proto);
      return;
    }

    handler.onHandle(new HandlerContextImpl(proto, self.getRobotState(), instanceDep.getLujnet(),
        injectRoot.getProtoEncoder(), ctx.getActorRef(), instanceDep.getLujbean()));
  }

//  private static final Logger LOG = LoggerFactory.getLogger(OnReceiveProto.class);
}
