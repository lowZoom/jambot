package luj.game.robot.internal.start;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import luj.ava.reflect.type.TypeX;
import luj.cluster.api.node.NodeStartListener;
import luj.game.robot.api.network.RobotProtoHandler;
import luj.game.robot.internal.admin.actor.BotAdminActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMapFactory;
import luj.game.robot.internal.concurrent.parent.RobotParentActor;
import luj.game.robot.internal.dynamic.combine.AllBeanCombiner;
import luj.game.robot.internal.start.listener.BotStartListenTrigger;
import luj.spring.anno.Internal;

@Internal
final class OnLujclusterStart implements NodeStartListener {

  @Override
  public void onStart(Context ctx) {
    BotbeanInLujcluster botbean = ctx.getStartParam();
    var adminActor = new BotAdminActor();
    Actor adminRef = ctx.createApplicationActor(adminActor);

    var parentActor = new RobotParentActor();
    parentActor.setRobotList(new ArrayList<>());
    parentActor.setAdminRef(adminRef);
    parentActor.setInstanceDependency(collectInstanceDependency(botbean));

    Actor parentRef = ctx.createApplicationActor(parentActor);
    new BotStartListenTrigger(parentRef, botbean.getInjectRoot().startListener()).trigger();
  }

  private RobotInstanceDependency collectInstanceDependency(BotbeanInLujcluster botbean) {
    AllBeanCombiner.Result rootBean = botbean.getInjectRoot();
    CommandMap cmdMap = new CommandMapFactory(rootBean).create();

    Map<String, RobotProtoHandler<?>> handleMap = rootBean.protoHandler().stream()
        .collect(toMap(this::getProtoType, Function.identity()));

    return new RobotInstanceDependency(rootBean.instanceRoot(),
        botbean.getLujbean(), rootBean.createListener(), cmdMap, handleMap);
  }

  private String getProtoType(RobotProtoHandler<?> handler) {
    return TypeX.ofInstance(handler)
        .getSupertype(RobotProtoHandler.class)
        .getTypeParam(0)
        .asClass()
        .getName();
  }
}
