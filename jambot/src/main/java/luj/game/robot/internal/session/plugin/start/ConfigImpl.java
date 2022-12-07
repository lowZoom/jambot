package luj.game.robot.internal.session.plugin.start;

import java.util.function.Function;
import luj.game.robot.api.plugin.JambotBootInit;

final class ConfigImpl implements JambotBootInit.Config {

  @Override
  public JambotBootInit.Config param(Function<JambotBootInit.Param, JambotBootInit.Param> p) {
    p.apply(_param);
    return this;
  }

  CParamImpl _param = new CParamImpl();
}
