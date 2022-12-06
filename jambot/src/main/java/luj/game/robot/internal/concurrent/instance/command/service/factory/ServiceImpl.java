package luj.game.robot.internal.concurrent.instance.command.service.factory;

import java.util.function.BiFunction;
import luj.game.robot.api.action.CommandService;
import luj.game.robot.internal.concurrent.instance.command.CommandExecuteStarterV2;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;

final class ServiceImpl<P> implements CommandService<P> {

  @Override
  public void execute(BiFunction<Param, P, Param> param) {
    CommandServiceFactory f = _factory;

    new CommandExecuteStarterV2(f._instanceRef, _cmd.getCommandType(),
        _cmd.getParamType(), param, f._lujbean, f._fromCmd, f._fromParam).start();
  }

  CommandMap.Command _cmd;

  CommandServiceFactory _factory;
}
