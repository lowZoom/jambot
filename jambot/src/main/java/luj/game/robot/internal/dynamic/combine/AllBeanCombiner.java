package luj.game.robot.internal.dynamic.combine;

import java.util.List;
import luj.bean.api.BeanContext;
import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.api.action.RobotDataCommand;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.network.RobotProtoHandler;
import luj.game.robot.internal.dynamic.init.DynamicInitInvoker;
import luj.game.robot.internal.session.inject.StaticBeanCollector;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;

public class AllBeanCombiner {

  public interface Result {

    List<RobotStartListener> startListener();

    List<RobotCreateListener> createListener();

    List<RobotDataCommand<?>> dataCommand();

    List<RobotProtoHandler<?>> protoHandler();

    RobotInstanceInjectRoot instanceRoot();

    BeanContext lujbean();
  }

  public AllBeanCombiner(StaticBeanCollector.Result staticRoot,
      DynamicInitInvoker.Result dynamicRoot, BeanContext lujbean) {
    _staticRoot = staticRoot;
    _dynamicRoot = dynamicRoot;
    _lujbean = lujbean;
  }

  // 如果有要做热更，需要考虑泄露问题
  public Result combine() {
    var result = new ResultImpl();
    result._staticRoot = _staticRoot;
    result._dynamicRoot = _dynamicRoot;
    result._lujbean = _lujbean;

    return result;
  }

  private final StaticBeanCollector.Result _staticRoot;
  private final DynamicInitInvoker.Result _dynamicRoot;

  private final BeanContext _lujbean;
}
