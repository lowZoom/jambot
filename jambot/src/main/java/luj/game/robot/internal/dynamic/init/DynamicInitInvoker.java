package luj.game.robot.internal.dynamic.init;

import java.util.List;
import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.api.action.RobotDataCommand;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.plugin.JambotDynamicInit;

public class DynamicInitInvoker {

  public interface Result {

    List<RobotStartListener> startListener();

    List<RobotCreateListener> createListener();

    List<RobotDataCommand<?>> dataCommand();
  }

  public DynamicInitInvoker(JambotDynamicInit initPlugin, Object startParam) {
    _initPlugin = initPlugin;
    _startParam = startParam;
  }

  public Result invoke() {
    var ctx = new ContextImpl();
    ctx._startParam = _startParam;

    // 调用外部初始化逻辑
    _initPlugin.onInit(ctx);

    return makeResult(ctx);
  }

  private Result makeResult(ContextImpl ctx) {
    var result = new ResultImpl();
    result._start = ctx.findBeans(RobotStartListener.class);

    result._create = ctx.findBeans(RobotCreateListener.class);
    result._command = ctx.findBeans(RobotDataCommand.class);

    return result;
  }

  private final JambotDynamicInit _initPlugin;

  private final Object _startParam;
}
