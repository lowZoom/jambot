package luj.game.robot.internal.concurrent.instance.command.map;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.function.Function;
import luj.ava.reflect.type.TypeX;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.internal.session.inject.RobotBeanCollector;

public class CommandMapFactory {

  public CommandMapFactory(RobotBeanCollector.Result rootBean) {
    _rootBean = rootBean;
  }

  public CommandMap create() {
    Map<Class<?>, CommandMap.Command> cmdMap = _rootBean.getCommandList().stream()
        .map(this::createCommand)
        .collect(toMap(CommandImpl::getCommandType, Function.identity()));

    return new CommandMapImpl(cmdMap);
  }

  private CommandImpl createCommand(RobotCommand<?> cmdInstance) {
    CommandImpl cmd = new CommandImpl();
    cmd._command = cmdInstance;

    Class<?> cmdType = cmdInstance.getClass();
    cmd._commandType = cmdType;

    cmd._paramType = TypeX.of(cmdType)
        .getSupertype(RobotCommand.class)
        .getTypeParam(0)
        .asClass();

    return cmd;
  }

  private final RobotBeanCollector.Result _rootBean;
}
