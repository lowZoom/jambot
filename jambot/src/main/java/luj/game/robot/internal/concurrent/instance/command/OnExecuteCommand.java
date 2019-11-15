package luj.game.robot.internal.concurrent.instance.command;

import java.util.Map;
import luj.ava.spring.Internal;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Internal
final class OnExecuteCommand implements RobotInstanceActor.Handler<BotExecuteCommandMsg> {

  @Override
  public void onHandle(Context ctx) {
    BotExecuteCommandMsg msg = ctx.getMessage(this);
    RobotInstanceActor actor = ctx.getActorState(this);

    RobotInstanceDependency dep = actor.getDependency();
    Map<Class<?>, RobotCommand> cmdMap = dep.getCommandMap();

    Class<?> cmdType = msg.getCommandType();
    RobotCommand cmd = cmdMap.get(cmdType);
    if (cmd == null) {
      LOG.warn("指令未被启用：{}", cmdType.getName());
      return;
    }

    cmd.onExecute(new CommandContextImpl(actor.getRobotState(),
        dep.getInjectRoot().getProtoEncoder(), ctx.getActorRef()));
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnExecuteCommand.class);
}
