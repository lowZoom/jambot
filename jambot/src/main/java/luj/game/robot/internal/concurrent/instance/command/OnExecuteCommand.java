package luj.game.robot.internal.concurrent.instance.command;

import java.util.Map;
import luj.bean.api.BeanContext;
import luj.bean.api.bean.ImmutableBean;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;
import luj.game.robot.internal.concurrent.instance.command.service.CmdRandomImpl;
import luj.game.robot.internal.start.botinstance.RobotState;
import luj.spring.anno.Internal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Internal
final class OnExecuteCommand implements RobotInstanceActor.Handler<BotExecuteCommandMsg> {

  @Override
  public void onHandle(Context ctx) {
    RobotInstanceActor self = ctx.getActorState(this);
    BotExecuteCommandMsg msg = ctx.getMessage(this);

    RobotInstanceDependency dep = self.getDependency();
    CommandMap cmdMap = dep.getCommandMap();

    String cmdType = msg.commandType();
    CommandMap.Command cmd = cmdMap.get(cmdType);
    if (cmd == null) {
      LOG.warn("指令未被启用：{}", cmdType);
      return;
    }

    RobotState botState = self.getRobotState();
    Ref selfRef = ctx.getActorRef();

    var robot = new CmdRobotImpl();
    robot._botState = botState;
    robot._instanceRef = selfRef;
    robot._dependency = dep;
    robot._cmdType = cmdType;

    var execCtx = new CmdContextImpl();
    execCtx._param = makeParamObj(ctx, cmd);
    execCtx._robot = robot;
    execCtx._logger = cmd.getLogger();
    execCtx._service = makeService();

    cmd.getInstance().onExecute(execCtx);
  }

  private Object makeParamObj(Context ctx, CommandMap.Command cmd) {
    RobotInstanceActor self = ctx.getActorState(this);
    BotExecuteCommandMsg msg = ctx.getMessage(this);

    BeanContext lujbean = self.getDependency().getLujbean();
    Map<String, Object> paramMap = msg.param();

    ImmutableBean<?> bean = lujbean.createImmutable(cmd.getParamType(), paramMap);
    return bean.getValueInstance();
  }

  private CmdServiceImpl makeService() {
    var service = new CmdServiceImpl();
    service._random = new CmdRandomImpl();
    return service;
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnExecuteCommand.class);
}
