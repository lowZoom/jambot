package luj.game.robot.internal.concurrent.instance.command.service.factory;

import java.util.function.Supplier;
import luj.bean.api.bean.Bean;
import luj.game.robot.api.action.CommandService;

final class SParamImpl<T> implements CommandService.Param, CommandService.Field<T> {

  @SuppressWarnings("unchecked")
  @Override
  public <V> CommandService.Field<V> set(Supplier<V> field) {
    _field = (Supplier<T>) field;
    return (CommandService.Field<V>) this;
  }

  @SuppressWarnings("DollarSignInName")
  @Override
  public CommandService.Param $(T value) {
    _paramBean.setField(_field, value);
    return this;
  }

  Bean<?> _paramBean;

  Supplier<T> _field;
}
