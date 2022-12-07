package luj.game.robot.internal.concurrent.instance.command.service.factory;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import luj.bean.api.BeanContext;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.api.action.CommandService;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;

public class CommandServiceFactory {

  public CommandServiceFactory(ActorMessageHandler.Ref instanceRef, Map<String, Object> fromParam,
      String fromCmd, BeanContext lujbean, CommandMap commandMap, Class<?> cmdType) {
    _instanceRef = instanceRef;
    _fromParam = fromParam;
    _fromCmd = fromCmd;
    _lujbean = lujbean;
    _commandMap = commandMap;
    _cmdType = cmdType;
  }

  public <P> CommandService<P> create() {
    String cmdName = _cmdType.getName();
    CommandMap.Command cmd = _commandMap.get(cmdName);
    checkNotNull(cmd, cmdName);

    var service = new ServiceImpl<P>();
    service._cmd = cmd;
    service._fromParam = MoreObjects.firstNonNull(_fromParam, ImmutableMap.of());
    service._factory = this;

    return service;
  }

  final ActorMessageHandler.Ref _instanceRef;

  final String _fromCmd;
  final BeanContext _lujbean;

  private final CommandMap _commandMap;
  private final Class<?> _cmdType;

  private final Map<String, Object> _fromParam;
}
