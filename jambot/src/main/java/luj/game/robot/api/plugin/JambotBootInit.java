package luj.game.robot.api.plugin;

import java.util.function.Function;

public interface JambotBootInit {

  interface Context {

    //TODO: 管理节点的引用

    Config configure(Function<Config, Config> c);
  }

  interface Config {

    Config param(Function<Param, Param> p);
  }

  interface Param {

    Param start(Object val);
  }

  Config onInit(Context ctx);
}
