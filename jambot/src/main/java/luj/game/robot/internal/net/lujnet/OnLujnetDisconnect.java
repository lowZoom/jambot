package luj.game.robot.internal.net.lujnet;

import luj.ava.spring.Internal;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.concurrent.instance.network.disconnect.BotNetDisconnectMsg;
import luj.net.api.connection.NetDisconnectListener;

@Internal
final class OnLujnetDisconnect implements NetDisconnectListener {

  /**
   * @see luj.game.robot.internal.net.disconnect.BotDisconnectInvoker#invoke
   */
  @Override
  public void onDisconnect(Context ctx) {
    Tellable instanceRef = ctx.getApplicationParam();

    instanceRef.tell(BotNetDisconnectMsg.INSTANCE);
  }
}
