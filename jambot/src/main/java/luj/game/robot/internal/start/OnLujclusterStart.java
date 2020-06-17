package luj.game.robot.internal.start;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import luj.ava.reflect.type.TypeX;
import luj.ava.spring.Internal;
import luj.cluster.api.node.NodeStartListener;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.admin.actor.BotAdminActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMapFactory;
import luj.game.robot.internal.concurrent.parent.RobotParentActor;
import luj.game.robot.internal.session.inject.RobotBeanCollector;
import luj.game.robot.internal.start.listener.BotStartListenTrigger;

@Internal
final class OnLujclusterStart implements NodeStartListener {

  @Override
  public void onStart(Context ctx) {
    BotbeanInLujcluster botbean = ctx.getStartParam();
    RobotBeanCollector.Result rootBean = botbean.getInjectRoot();

    BotAdminActor adminActor = new BotAdminActor();
    Actor adminRef = ctx.createApplicationActor(adminActor);

    RobotParentActor parentActor = new RobotParentActor();
    parentActor.setRobotList(new ArrayList<>());
    parentActor.setAdminRef(adminRef);
    parentActor.setInstanceDependency(collectInstanceDependency(botbean));

    Actor parentRef = ctx.createApplicationActor(parentActor);
    new BotStartListenTrigger(parentRef, rootBean.getStartListeners()).trigger();
  }

  private RobotInstanceDependency collectInstanceDependency(BotbeanInLujcluster botbean) {
    RobotBeanCollector.Result rootBean = botbean.getInjectRoot();
    CommandMap cmdMap = new CommandMapFactory(rootBean).create();

    Map<Class<?>, RobotProtoHandler<?>> handleMap = rootBean.getProtoHandlerList().stream()
        .collect(toMap(this::getProtoType, Function.identity()));

    return new RobotInstanceDependency(rootBean.getInstanceInjectRoot(),
        botbean.getLujnet(), botbean.getLujbean(), handleMap, cmdMap);
  }

  private Class<?> getProtoType(RobotProtoHandler<?> handler) {
    return TypeX.ofInstance(handler)
        .getSupertype(RobotProtoHandler.class)
        .getTypeParam(0)
        .asClass();
  }
}
