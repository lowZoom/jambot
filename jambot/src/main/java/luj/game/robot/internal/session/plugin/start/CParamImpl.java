package luj.game.robot.internal.session.plugin.start;

import luj.game.robot.api.plugin.JambotBootInit;

final class CParamImpl implements JambotBootInit.Param {

  @Override
  public JambotBootInit.Param start(Object val) {
    _start = val;
    return this;
  }

  Object _start;
}
