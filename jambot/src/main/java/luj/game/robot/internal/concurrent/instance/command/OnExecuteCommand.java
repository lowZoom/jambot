package luj.game.robot.internal.concurrent.instance.command;

import luj.ava.spring.Internal;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;
import luj.game.robot.internal.concurrent.instance.command.service.CmdRandomImpl;
import luj.game.robot.internal.start.botinstance.RobotState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Internal
final class OnExecuteCommand implements RobotInstanceActor.Handler<BotExecuteCommandMsg> {

  @Override
  public void onHandle(Context ctx) {
    BotExecuteCommandMsg msg = ctx.getMessage(this);
    RobotInstanceActor self = ctx.getActorState(this);

    RobotInstanceDependency dep = self.getDependency();
    CommandMap cmdMap = dep.getCommandMap();

    Class<?> cmdType = msg.getCommandType();
    CommandMap.Command cmd = cmdMap.get(cmdType);
    if (cmd == null) {
      LOG.warn("指令未被启用：{}", cmdType.getName());
      return;
    }

    CmdContextImpl execCtx = new CmdContextImpl();
    execCtx._botIndex = self.getIndex();

    RobotState botState = self.getRobotState();
    execCtx._botState = botState;

    Ref selfRef = ctx.getActorRef();
    execCtx._instanceRef = selfRef;
    execCtx._service = makeService(selfRef, botState, dep);

    execCtx._param = msg.getParam();
    execCtx._logger = cmd.getLogger();

    cmd.getInstance().onExecute(execCtx);
  }

  private CmdServiceImpl makeService(Ref instanceRef, RobotState botState,
      RobotInstanceDependency dep) {
    CmdServiceImpl service = new CmdServiceImpl();
    service._random = new CmdRandomImpl();
    return service;
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnExecuteCommand.class);
}
