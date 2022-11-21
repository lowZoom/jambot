package luj.game.robot.internal.start;

import java.util.List;
import luj.bean.api.BeanContext;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.internal.session.inject.RobotBeanCollector;

public class BotbeanInLujcluster {

  public BotbeanInLujcluster(RobotBeanCollector.Result injectRoot,
      List<RobotStartListener> startListeners, BeanContext lujbean) {
    _injectRoot = injectRoot;
    _startListeners = startListeners;
    _lujbean = lujbean;
  }

  public RobotBeanCollector.Result getInjectRoot() {
    return _injectRoot;
  }

  public List<RobotStartListener> getStartListeners() {
    return _startListeners;
  }

  public BeanContext getLujbean() {
    return _lujbean;
  }

  private final RobotBeanCollector.Result _injectRoot;
  private final List<RobotStartListener> _startListeners;

  private final BeanContext _lujbean;
}
