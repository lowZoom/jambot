package luj.game.robot.internal.concurrent.instance.command;

import java.util.Map;
import luj.ava.spring.Internal;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;

@Internal
final class OnExecuteCommand implements RobotInstanceActor.Handler<BotExecuteCommandMsg> {

  @Override
  public void onHandle(Context ctx) {
    BotExecuteCommandMsg msg = ctx.getMessage(this);
    RobotInstanceActor actor = ctx.getActorState(this);

    RobotInstanceDependency dep = actor.getDependency();
    Map<Class<?>, RobotCommand> cmdMap = dep.getCommandMap();
    RobotCommand cmd = cmdMap.get(msg.getCommandType());

    cmd.onExecute(new CommandContextImpl(actor.getRobotState(),
        dep.getInjectRoot().getProtoEncoder(), ctx.getActorRef()));
  }
}
