package luj.game.robot.internal.sessionv2.plugin.start;

import java.util.function.Function;
import luj.game.robot.api.plugin.JambotBootInit;

final class ContextImpl implements JambotBootInit.Context {

  @Override
  public JambotBootInit.Config configure(Function<JambotBootInit.Config, JambotBootInit.Config> c) {
    c.apply(_config);
    return _config;
  }

  ConfigImpl _config;
}
