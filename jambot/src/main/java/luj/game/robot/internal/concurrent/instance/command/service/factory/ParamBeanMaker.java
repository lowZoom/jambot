package luj.game.robot.internal.concurrent.instance.command.service.factory;

import java.util.function.BiConsumer;
import luj.bean.api.BeanContext;
import luj.bean.api.bean.Bean;
import luj.game.robot.api.action.CommandService;

public class ParamBeanMaker {

  public ParamBeanMaker(Class<?> paramType, BiConsumer<CommandService.Param, Object> buildCaller,
      BeanContext lujbean) {
    _paramType = paramType;
    _buildCaller = buildCaller;
    _lujbean = lujbean;
  }

  public Bean<?> make() {
    var builder = new SParamImpl<>();
    Bean<?> paramBean = _lujbean.createBean(_paramType);

    builder._paramBean = paramBean;
    _buildCaller.accept(builder, paramBean.getSetterInstance());

    return paramBean;
  }

  private final Class<?> _paramType;
  private final BiConsumer<CommandService.Param, Object> _buildCaller;

  private final BeanContext _lujbean;
}
