package luj.game.robot.internal.start;

import luj.bean.api.BeanContext;
import luj.game.robot.internal.dynamic.combine.AllBeanCombiner;

public class BotbeanInLujcluster {

  public BotbeanInLujcluster(AllBeanCombiner.Result injectRoot, BeanContext lujbean) {
    _injectRoot = injectRoot;
    _lujbean = lujbean;
  }

  public AllBeanCombiner.Result getInjectRoot() {
    return _injectRoot;
  }

  public BeanContext getLujbean() {
    return _lujbean;
  }

  private final AllBeanCombiner.Result _injectRoot;

  private final BeanContext _lujbean;
}
