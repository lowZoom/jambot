package luj.game.robot.internal.concurrent.instance.command;

import java.util.Map;
import java.util.function.BiFunction;
import luj.bean.api.BeanContext;
import luj.bean.api.bean.Bean;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.api.action.CommandService;
import luj.game.robot.internal.concurrent.instance.command.service.factory.ParamBeanMaker;

public class CommandExecuteStarterV2 {

  public CommandExecuteStarterV2(Tellable instanceRef, Class<?> targetCmd, Class<?> paramType,
      BiFunction<CommandService.Param, ?, CommandService.Param> paramBuilder,
      BeanContext lujbean, String fromCmd, Map<String, Object> fromParam) {
    _instanceRef = instanceRef;
    _targetCmd = targetCmd;
    _paramType = paramType;
    _paramBuilder = paramBuilder;
    _lujbean = lujbean;
    _fromCmd = fromCmd;
    _fromParam = fromParam;
  }

  public void start() {
    String targetCmd = _targetCmd.getName();
    Map<String, Object> param = resolveParam(targetCmd);

    var msg = new BotExecuteCommandMsg(targetCmd, param);
    _instanceRef.tell(msg);
  }

  @SuppressWarnings("unchecked")
  private Map<String, Object> resolveParam(String targetCmd) {
    if (targetCmd.equals(_fromCmd)) {
      return _fromParam;
    }

    var builder = (BiFunction<CommandService.Param, Object, CommandService.Param>) _paramBuilder;
    Bean<?> paramBean = new ParamBeanMaker(_paramType, builder::apply, _lujbean).make();
    return paramBean.getFieldMap();
  }

  private final Tellable _instanceRef;
  private final Class<?> _targetCmd;

  private final Class<?> _paramType;
  private final BiFunction<CommandService.Param, ?, CommandService.Param> _paramBuilder;
  private final BeanContext _lujbean;

  private final String _fromCmd;
  private final Map<String, Object> _fromParam;
}
