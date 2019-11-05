package luj.game.robot.internal.start;

import luj.ava.spring.Internal;
import luj.cluster.api.node.NodeStartListener;
import luj.game.robot.internal.session.inject.RobotBeanCollector;
import luj.game.robot.internal.start.listener.BotStartListenTrigger;

@Internal
final class OnLujclusterStart implements NodeStartListener {

  @Override
  public void onStart(Context ctx) {
    RobotBeanCollector.Result rootBean = ctx.getStartParam();

    new BotStartListenTrigger(rootBean.getStartListeners(), rootBean.getProtoEncoder()).trigger();
  }
}
