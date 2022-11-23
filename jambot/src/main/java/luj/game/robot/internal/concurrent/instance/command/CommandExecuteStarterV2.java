package luj.game.robot.internal.concurrent.instance.command;

import luj.cluster.api.actor.Tellable;

public class CommandExecuteStarterV2 {

  public CommandExecuteStarterV2(Tellable instanceRef, Class<?> targetCmd, String fromCmd,
      Object fromParam) {
    _instanceRef = instanceRef;
    _targetCmd = targetCmd;
    _fromCmd = fromCmd;
    _fromParam = fromParam;
  }

  public void start() {
    String targetCmd = _targetCmd.getName();
    Object param = resolveParam(targetCmd);

    var msg = new BotExecuteCommandMsg(targetCmd, param);
    _instanceRef.tell(msg);
  }

  private Object resolveParam(String targetCmd) {
    if (targetCmd.equals(_fromCmd)) {
      return _fromParam;
    }

    //TODO: 后面要能传递自定义参数
    return null;
  }

  private final Tellable _instanceRef;
  private final Class<?> _targetCmd;

  private final String _fromCmd;
  private final Object _fromParam;
}
