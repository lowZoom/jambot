package luj.game.robot.internal.net.receive;

import java.util.Map;
import java.util.Queue;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.instance.tick.wait.WaitStepFinishTrier;
import luj.game.robot.internal.instance.tick.wait.WaitingProtoChecker;
import luj.game.robot.internal.start.botinstance.RobotState;

public class BotProtoReceiver {

  public BotProtoReceiver(byte[] protoData, RobotInstanceActor instanceState,
      ActorMessageHandler.Ref instanceRef) {
    _protoData = protoData;
    _instanceState = instanceState;
    _instanceRef = instanceRef;
  }

  public void receive() throws Exception {
    RobotInstanceDependency instanceDep = _instanceState.getDependency();
    RobotProtoDecoder protoDecoder = instanceDep.getInjectRoot().getProtoDecoder();

    DecodeContextImpl decodeCtx = new DecodeContextImpl(_protoData);
    Object proto = protoDecoder.decode(decodeCtx);
    Class<?> protoType = proto.getClass();

    RobotState botState = _instanceState.getRobotState();
    Queue<Class<?>> history = botState.getReceiveHistory();
    history.offer(protoType);

    handleProto(proto, protoType);

    if (new WaitingProtoChecker(botState).isWaiting()) {
      new WaitStepFinishTrier(botState, _instanceRef, instanceDep.getLujbean()).tryFinish();
    }
  }

  private void handleProto(Object proto, Class<?> protoType) {
    RobotInstanceDependency dep = _instanceState.getDependency();
    Map<Class<?>, RobotProtoHandler<?>> handlerMap = dep.getProtoHandleMap();
    RobotProtoHandler<?> handler = handlerMap.get(protoType);

    if (handler == null) {
//      LOG.debug("未处理的协议包：{}，{}", proto.getClass().getName(), proto);
      return;
    }

    handler.onHandle(new HandlerContextImpl(proto, _instanceState.getRobotState(),
        dep.getLujnet(), dep.getInjectRoot().getProtoEncoder(), _instanceRef, dep.getLujbean()));
  }

  private final byte[] _protoData;

  private final RobotInstanceActor _instanceState;
  private final ActorMessageHandler.Ref _instanceRef;
}
