package luj.game.robot.internal.concurrent.instance.command.map;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.function.Function;
import luj.ava.reflect.type.TypeX;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.internal.dynamic.combine.AllBeanCombiner;
import org.slf4j.LoggerFactory;

public class CommandMapFactory {

  public CommandMapFactory(AllBeanCombiner.Result rootBean) {
    _rootBean = rootBean;
  }

  public CommandMap create() {
    Map<String, CommandMap.Command> cmdMap = _rootBean.command().stream()
        .map(this::createCommand)
        .collect(toMap(CommandImpl::getCommandName, Function.identity()));

    var result = new CommandMapImpl();
    result._cmdMap = cmdMap;

    return result;
  }

  private CommandImpl createCommand(RobotCommand<?> cmdInstance) {
    CommandImpl cmd = new CommandImpl();
    cmd._command = cmdInstance;

    Class<?> cmdType = cmdInstance.getClass();
    cmd._commandType = cmdType;
    cmd._logger = LoggerFactory.getLogger(cmdType);

    cmd._paramType = TypeX.of(cmdType)
        .getSupertype(RobotCommand.class)
        .getTypeParam(0)
        .asClass();

    return cmd;
  }

  private final AllBeanCombiner.Result _rootBean;
}
