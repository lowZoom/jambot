package luj.game.robot.internal.concurrent.instance.network.receive;

import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.net.receive.BotProtoReceiverV2;
import luj.spring.anno.Internal;

@Internal
final class OnReceiveProto implements RobotInstanceActor.Handler<BotReceiveProtoMsg> {

  @Override
  public void onHandle(Context ctx) {
    RobotInstanceActor self = ctx.getActorState(this);
    BotReceiveProtoMsg msg = ctx.getMessage(this);

    new BotProtoReceiverV2(msg.protoData(), self, ctx.getActorRef()).receive();
  }

//  private static final Logger LOG = LoggerFactory.getLogger(OnReceiveProto.class);
}
