package luj.game.robot.internal.net.lujnet;

import io.netty.buffer.ByteBuf;
import luj.ava.spring.Internal;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.concurrent.instance.network.receive.BotReceiveProtoMsg;
import luj.net.api.connection.NetReceiveListener;

@Internal
final class OnLujnetReceive implements NetReceiveListener {

  @Override
  public void onReceive(Context ctx) {
    ByteBuf dataBuf = ctx.getData();
    byte[] data = new byte[dataBuf.readableBytes()];
    dataBuf.readBytes(data);

    Tellable instanceRef = ctx.getApplicationParam();
    BotReceiveProtoMsg msg = new BotReceiveProtoMsg(data);
    instanceRef.tell(msg);
  }

//  private static final Logger LOG = LoggerFactory.getLogger(OnLujnetReceive.class);
}
