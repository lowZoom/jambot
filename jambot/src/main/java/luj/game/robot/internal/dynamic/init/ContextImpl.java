package luj.game.robot.internal.dynamic.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import luj.game.robot.api.plugin.JambotDynamicInit;

final class ContextImpl implements JambotDynamicInit.Context {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getStartParam() {
    return (T) _startParam;
  }

  @Override
  public void registerAll(Collection<?> beans) {
    _registerAll.addAll(beans);
  }

  @SuppressWarnings("unchecked")
  <T> List<T> findBeans(Class<? super T> type) {
    return _registerAll.stream()
        .filter(c -> type.isAssignableFrom(c.getClass()))
        .map(c -> (T) c)
        .collect(Collectors.toList());
  }

  List<Object> _registerAll = new ArrayList<>();

  Object _startParam;
}
