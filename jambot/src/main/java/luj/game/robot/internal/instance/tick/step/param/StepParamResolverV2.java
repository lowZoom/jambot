package luj.game.robot.internal.instance.tick.step.param;

import java.util.Map;
import luj.bean.api.BeanContext;

public class StepParamResolverV2 {

  public StepParamResolverV2(Map<String, Object> stepRaw, Class<?> paramType, BeanContext lujbean) {
    _stepRaw = stepRaw;
    _paramType = paramType;
    _lujbean = lujbean;
  }

  public Map<String, Object> resolve() {
    Map<String, Object> valueMap = _stepRaw;// _stepRaw.resolveWith(_actionParam).entrySet()

//    ImmutableBean<?> bean = _lujbean.createImmutable(_paramType, valueMap);
    return valueMap;// bean.getValueInstance();
  }

  private final Map<String, Object> _stepRaw;

  private final Class<?> _paramType;
  private final BeanContext _lujbean;
}
