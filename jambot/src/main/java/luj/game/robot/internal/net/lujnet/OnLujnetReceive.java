package luj.game.robot.internal.net.lujnet;

import luj.ava.spring.Internal;
import luj.net.api.data.NetReceiveListener;

@Internal
final class OnLujnetReceive implements NetReceiveListener {

  @Override
  public void onReceive(Context ctx) {
    System.out.println("服务器回了数据：" + ctx.data().getClass());

  }
}
