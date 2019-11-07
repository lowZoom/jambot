package luj.game.robot.internal.start;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import luj.ava.spring.Internal;
import luj.cluster.api.node.NodeStartListener;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.concurrent.parent.RobotParentActor;
import luj.game.robot.internal.session.inject.RobotBeanCollector;
import luj.game.robot.internal.start.listener.BotStartListenTrigger;

@Internal
final class OnLujclusterStart implements NodeStartListener {

  @Override
  public void onStart(Context ctx) {
    BotbeanInLujcluster botbean = ctx.getStartParam();
    RobotBeanCollector.Result rootBean = botbean.getInjectRoot();

    RobotParentActor parentActor = new RobotParentActor();
    parentActor.setRobotList(new ArrayList<>());
    parentActor.setInstanceDependency(collectInstanceDependency(botbean));

    Actor parentRef = ctx.createApplicationActor(parentActor);
    new BotStartListenTrigger(parentRef, rootBean.getStartListeners()).trigger();
  }

  private RobotInstanceDependency collectInstanceDependency(BotbeanInLujcluster botbean) {
    RobotBeanCollector.Result rootBean = botbean.getInjectRoot();

    Map<Class<?>, RobotCommand> cmdMap = rootBean.getCommandList().stream()
        .collect(Collectors.toMap(RobotCommand::getClass, Function.identity()));

    return new RobotInstanceDependency(rootBean.getInstanceInjectRoot(),
        botbean.getLujnet(), cmdMap);
  }
}
