package luj.game.robot.internal.start;

import luj.ava.spring.Internal;
import luj.cluster.api.node.NodeStartListener;
import luj.game.robot.internal.session.inject.RobotBeanCollector;
import luj.game.robot.internal.start.listener.BotStartListenTrigger;

@Internal
final class OnLujclusterStart implements NodeStartListener {

  @Override
  public void onStart(Context ctx) {
    BotbeanInLujcluster botbean = ctx.getStartParam();
    RobotBeanCollector.Result rootBean = botbean.getInjectRoot();

    new BotStartListenTrigger(rootBean.getStartListeners(),
        botbean.getLujnet(), rootBean.getProtoEncoder()).trigger();
  }
}
