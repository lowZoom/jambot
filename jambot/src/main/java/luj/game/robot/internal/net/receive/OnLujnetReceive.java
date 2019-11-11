package luj.game.robot.internal.net.receive;

import io.netty.buffer.ByteBuf;
import luj.ava.spring.Internal;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.game.robot.internal.concurrent.instance.receive.BotReceiveProtoMsg;
import luj.net.api.NetConnection;
import luj.net.api.data.NetReceiveListener;

@Internal
final class OnLujnetReceive implements NetReceiveListener {

  @Override
  public void onReceive(Context ctx) {
    ByteBuf dataBuf = ctx.getData();
    byte[] data = new byte[dataBuf.readableBytes()];
    dataBuf.readBytes(data);

    NetConnection conn = ctx.getConnection();
    ActorPreStartHandler.Actor instanceRef = conn.getApplicationParam();

    BotReceiveProtoMsg msg = new BotReceiveProtoMsg(data);
    instanceRef.tell(msg);
  }

//  private static final Logger LOG = LoggerFactory.getLogger(OnLujnetReceive.class);
}
