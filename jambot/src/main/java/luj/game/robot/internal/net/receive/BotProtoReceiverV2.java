package luj.game.robot.internal.net.receive;

import java.util.Map;
import java.util.Queue;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.api.network.RobotProtoHandler;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.instance.tick.wait.WaitStepFinishTrier;
import luj.game.robot.internal.instance.tick.wait.WaitingProtoChecker;
import luj.game.robot.internal.start.botinstance.RobotState;

public class BotProtoReceiverV2 {

  public BotProtoReceiverV2(String protoKey, Object protoObj, RobotInstanceActor instanceState,
      ActorMessageHandler.Ref instanceRef) {
    _protoKey = protoKey;
    _protoObj = protoObj;
    _instanceState = instanceState;
    _instanceRef = instanceRef;
  }

  public void receive() {
    Class<?> protoType = _protoObj.getClass();

    RobotState botState = _instanceState.getRobotState();
    Queue<Class<?>> history = botState.getReceiveHistory();
    history.offer(protoType);

    handleProto(_protoObj, _protoKey);

    if (new WaitingProtoChecker(botState).isWaiting()) {
      new WaitStepFinishTrier(botState, _instanceRef).tryFinish();
    }
  }

  private void handleProto(Object proto, String protoKey) {
    RobotInstanceDependency dep = _instanceState.getDependency();
    Map<String, RobotProtoHandler<?>> handlerMap = dep.getProtoHandleMap();

    RobotProtoHandler<?> handler = handlerMap.get(protoKey);
    if (handler == null) {
//      LOG.debug("未处理的协议包：{}，{}", proto.getClass().getName(), proto);
      return;
    }

    var handleCtx = new HandlerContextImpl();
    handleCtx._proto = proto;
    handleCtx._robotState = _instanceState.getRobotState();
    handleCtx._instanceRef = _instanceRef;
    handleCtx._lujbean = dep.getLujbean();

    handler.onHandle(handleCtx);
  }

//  private static final Logger LOG = LoggerFactory.getLogger(BotProtoReceiverV2.class);

  private final String _protoKey;
  private final Object _protoObj;

  private final RobotInstanceActor _instanceState;
  private final ActorMessageHandler.Ref _instanceRef;
}
