package luj.game.robot.internal.instance.tick.step.param;

import static java.util.stream.Collectors.toMap;

import com.typesafe.config.Config;
import java.util.Map;
import luj.bean.api.BeanContext;
import luj.bean.api.bean.ImmutableBean;

/**
 * @see StepParamResolverV2
 */
@Deprecated
public class StepParamResolver {

  public StepParamResolver(Config stepRaw, Config actionParam, Class<?> paramType,
      BeanContext lujbean) {
    _stepRaw = stepRaw;
    _actionParam = actionParam;
    _paramType = paramType;
    _lujbean = lujbean;
  }

  public Object resolve() {
    Map<String, Object> valueMap = _stepRaw.resolveWith(_actionParam).entrySet()
        .stream().collect(toMap(Map.Entry::getKey, e -> e.getValue().unwrapped()));

    ImmutableBean<?> bean = _lujbean.createImmutable(_paramType, valueMap);
    return bean.getValueInstance();
  }

  private final Config _stepRaw;
  private final Config _actionParam;

  private final Class<?> _paramType;
  private final BeanContext _lujbean;
}
