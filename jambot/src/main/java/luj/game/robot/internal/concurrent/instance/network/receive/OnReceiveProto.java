package luj.game.robot.internal.concurrent.instance.network.receive;

import java.util.Map;
import java.util.Queue;
import luj.ava.spring.Internal;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;

@Internal
final class OnReceiveProto implements RobotInstanceActor.Handler<BotReceiveProtoMsg> {

  @Override
  public void onHandle(Context ctx) throws Exception {
    RobotInstanceActor self = ctx.getActorState(this);
    BotReceiveProtoMsg msg = ctx.getMessage(this);

    RobotInstanceDependency instanceDep = self.getDependency();
    RobotProtoDecoder protoDecoder = instanceDep.getInjectRoot().getProtoDecoder();

    DecodeContextImpl decodeCtx = new DecodeContextImpl(msg.getProtoData());
    Object proto = protoDecoder.decode(decodeCtx);
    Class<?> protoType = proto.getClass();

    Queue<Class<?>> history = self.getRobotState().getReceiveHistory();
    history.offer(protoType);

    handleProto(proto, protoType, self, ctx.getActorRef());
  }

  private void handleProto(Object proto, Class<?> protoType,
      RobotInstanceActor self, ActorMessageHandler.Ref selfRef) {
    RobotInstanceDependency dep = self.getDependency();

    Map<Class<?>, RobotProtoHandler<?>> handlerMap = dep.getProtoHandleMap();
    RobotProtoHandler<?> handler = handlerMap.get(protoType);

    if (handler == null) {
//      LOG.debug("未处理的协议包：{}，{}", proto.getClass().getName(), proto);
      return;
    }

    handler.onHandle(new HandlerContextImpl(proto, self.getRobotState(), dep.getLujnet(),
        dep.getInjectRoot().getProtoEncoder(), selfRef, dep.getLujbean()));
  }

//  private static final Logger LOG = LoggerFactory.getLogger(OnReceiveProto.class);
}
