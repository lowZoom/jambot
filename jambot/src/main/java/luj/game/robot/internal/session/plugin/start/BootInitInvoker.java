package luj.game.robot.internal.session.plugin.start;

import luj.game.robot.api.plugin.JambotBootInit;

public class BootInitInvoker {

  public interface Result {

    Object startParam();
  }

  public BootInitInvoker(JambotBootInit initPlugin) {
    _initPlugin = initPlugin;
  }

  public Result invoke() {
    var ctx = new BootContextImpl();
    ctx._config = new ConfigImpl();

    // 调用外部逻辑
    var config = (ConfigImpl) _initPlugin.onInit(ctx);

    return new Result() {
      @Override
      public Object startParam() {
        return config._param._start;
      }
    };
  }

  private final JambotBootInit _initPlugin;
}
