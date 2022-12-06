package luj.game.robot.internal.dynamic.combine;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.bean.api.BeanContext;
import luj.game.robot.api.action.RobotDataCommand;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.dynamic.init.DynamicInitInvoker;
import luj.game.robot.internal.session.inject.StaticBeanCollector;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;

final class ResultImpl implements AllBeanCombiner.Result {

  @Override
  public List<RobotStartListener> startListener() {
    return ImmutableList.<RobotStartListener>builder()
        .addAll(_staticRoot.getStartListeners())
        .addAll(_dynamicRoot.startListeners())
        .build();
  }

  @Override
  public List<RobotDataCommand<?>> command() {
    return ImmutableList.<RobotDataCommand<?>>builder()
        .addAll(_staticRoot.getCommandList())
        .addAll(_dynamicRoot.commandList())
        .build();
  }

  @Override
  public List<RobotProtoHandler<?>> protoHandler() {
    return ImmutableList.of();
  }

  @Override
  public RobotInstanceInjectRoot instanceRoot() {
    return _staticRoot.getInstanceInjectRoot();
  }

  @Override
  public BeanContext lujbean() {
    return _lujbean;
  }

  StaticBeanCollector.Result _staticRoot;
  DynamicInitInvoker.Result _dynamicRoot;

  BeanContext _lujbean;
}
