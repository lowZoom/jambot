package luj.game.robot.internal.sessionv2.plugin.start;

import luj.game.robot.api.plugin.JambotBootInit;

public class BootInitInvoker {

  public interface Result {

    Object startParam();
  }

  public BootInitInvoker(JambotBootInit initPlugin) {
    _initPlugin = initPlugin;
  }

  public Result invoke() {
    ContextImpl ctx = new ContextImpl();
    ctx._config = new ConfigImpl();

    ConfigImpl config = (ConfigImpl) _initPlugin.onInit(ctx);

    return new Result() {
      @Override
      public Object startParam() {
        return config._param._start;
      }
    };
  }

  private final JambotBootInit _initPlugin;
}
