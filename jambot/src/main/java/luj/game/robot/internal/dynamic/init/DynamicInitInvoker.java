package luj.game.robot.internal.dynamic.init;

import java.util.List;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.plugin.JambotDynamicInit;

public class DynamicInitInvoker {

  public interface Result {

    List<RobotStartListener> getStartListeners();
  }

  public DynamicInitInvoker(JambotDynamicInit initPlugin, Object startParam) {
    _initPlugin = initPlugin;
    _startParam = startParam;
  }

  public Result invoke() {
    ContextImpl ctx = new ContextImpl();
    ctx._startParam = _startParam;

    _initPlugin.onInit(ctx);

    return makeResult(ctx);
  }

  private Result makeResult(ContextImpl ctx) {
    ResultImpl result = new ResultImpl();
    result._start = ctx.findBeans(RobotStartListener.class);

    return result;
  }

  private final JambotDynamicInit _initPlugin;

  private final Object _startParam;
}
