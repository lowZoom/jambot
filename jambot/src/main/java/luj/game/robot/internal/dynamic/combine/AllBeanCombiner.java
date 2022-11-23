package luj.game.robot.internal.dynamic.combine;

import java.util.List;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.dynamic.init.DynamicInitInvoker;
import luj.game.robot.internal.session.inject.StaticBeanCollector;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;

public class AllBeanCombiner {

  public interface Result {

    List<RobotStartListener> startListener();

    List<RobotCommand<?>> command();

    List<RobotProtoHandler<?>> protoHandler();

    RobotInstanceInjectRoot instanceRoot();
  }

  public AllBeanCombiner(StaticBeanCollector.Result staticRoot,
      DynamicInitInvoker.Result dynamicRoot) {
    _staticRoot = staticRoot;
    _dynamicRoot = dynamicRoot;
  }

  // 如果有要做热更，要考虑泄露问题
  public Result combine() {
    var result = new ResultImpl();
    result._staticRoot = _staticRoot;
    result._dynamicRoot = _dynamicRoot;

    return result;
  }

  private final StaticBeanCollector.Result _staticRoot;

  private final DynamicInitInvoker.Result _dynamicRoot;
}
