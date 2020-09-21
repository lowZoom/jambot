package luj.game.robot.internal.concurrent.instance.network.receive;

import luj.ava.spring.Internal;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.net.receive.BotProtoReceiver;

@Internal
final class OnReceiveProto implements RobotInstanceActor.Handler<BotReceiveProtoMsg> {

  @Override
  public void onHandle(Context ctx) throws Exception {
    RobotInstanceActor self = ctx.getActorState(this);
    BotReceiveProtoMsg msg = ctx.getMessage(this);

    new BotProtoReceiver(msg.getProtoData(), self, ctx.getActorRef()).receive();
  }

//  private static final Logger LOG = LoggerFactory.getLogger(OnReceiveProto.class);
}
