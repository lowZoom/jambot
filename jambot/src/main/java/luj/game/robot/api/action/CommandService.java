package luj.game.robot.api.action;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public interface CommandService<P> {

  interface Param {

    <V> Field<V> set(Supplier<V> field);
  }

  interface Field<V> {

    Param $(V value);
  }

  void execute(BiFunction<Param, P, Param> param);
}
