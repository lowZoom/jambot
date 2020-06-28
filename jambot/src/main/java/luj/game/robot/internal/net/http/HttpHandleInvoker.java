package luj.game.robot.internal.net.http;

import static com.google.common.base.Preconditions.checkNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.concurrent.instance.command.BotExecuteCommandMsg;
import luj.game.robot.internal.concurrent.instance.command.map.CommandMap;

public class HttpHandleInvoker {

  public HttpHandleInvoker(String respStr, Class<?> cmdType, Tellable instanceRef,
      RobotInstanceDependency instanceDep) {
    _respStr = respStr;
    _cmdType = cmdType;
    _instanceRef = instanceRef;
    _instanceDep = instanceDep;
  }

  public void invoke() {
    Object param = getParam();
    _instanceRef.tell(new BotExecuteCommandMsg(_cmdType, param));
  }

  private Object getParam() {
    CommandMap.Command cmd = _instanceDep.getCommandMap().get(_cmdType);
    checkNotNull(cmd, _cmdType.getName());

    if (cmd.getParamType() == String.class) {
      return _respStr;
    }

    try {
      Map<String, Object> jsonMap = JACKSON.readValue(_respStr.trim(), Map.class);
      return _instanceDep.getLujbean().create(cmd.getParamType(), jsonMap);
    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private static final ObjectMapper JACKSON = new ObjectMapper();

  private final String _respStr;
  private final Class<?> _cmdType;

  private final Tellable _instanceRef;
  private final RobotInstanceDependency _instanceDep;
}
