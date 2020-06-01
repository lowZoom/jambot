package luj.game.robot.internal.concurrent.instance.command;

import java.util.Map;
import luj.ava.spring.Internal;
import luj.bean.api.BeanContext;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;
import luj.game.robot.internal.concurrent.instance.command.service.CmdNetworkImpl;
import luj.game.robot.internal.concurrent.instance.command.service.CmdRandomImpl;
import luj.game.robot.internal.start.botinstance.RobotState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    Ref selfRef = ctx.getActorRef();
    RobotState botState = self.getRobotState();

    cmd.getInstance().onExecute(new CmdContextImpl(botState, self.getIndex(), selfRef,
        makeService(selfRef, botState, dep), createParam(cmd.getParamType(), msg.getParam())));
  }

  private CmdServiceImpl makeService(Ref instanceRef, RobotState botState,
      RobotInstanceDependency dep) {
    CmdNetworkImpl network = new CmdNetworkImpl(dep.getLujnet(), instanceRef, botState,
        dep.getInjectRoot().getProtoEncoder());

    CmdRandomImpl random = new CmdRandomImpl();
    return new CmdServiceImpl(network, random);
  }

  private Object createParam(Class<?> paramType, Map<String, Object> paramValue) {
    if (paramType == Void.class || paramType == Object.class) {
      return null;
    }
    return _lujbean.create(paramType, paramValue);
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnExecuteCommand.class);

  @Autowired
  private BeanContext _lujbean;
}
