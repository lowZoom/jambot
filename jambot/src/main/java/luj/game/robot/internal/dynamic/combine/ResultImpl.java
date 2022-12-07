package luj.game.robot.internal.dynamic.combine;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.bean.api.BeanContext;
import luj.game.robot.api.action.RobotCreateListener;
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
        .addAll(_staticRoot.startListener())
        .addAll(_dynamicRoot.startListener())
        .build();
  }

  @Override
  public List<RobotCreateListener> createListener() {
    return ImmutableList.<RobotCreateListener>builder()
        .addAll(_staticRoot.instanceRoot().getCreateListener())
        .addAll(_dynamicRoot.createListener())
        .build();
  }

  @Override
  public List<RobotDataCommand<?>> dataCommand() {
    return ImmutableList.<RobotDataCommand<?>>builder()
        .addAll(_staticRoot.dataCommand())
        .addAll(_dynamicRoot.dataCommand())
        .build();
  }

  @Override
  public List<RobotProtoHandler<?>> protoHandler() {
    return ImmutableList.<RobotProtoHandler<?>>builder()
        .addAll(_staticRoot.protoHandler())
        .addAll(_dynamicRoot.protoHandler())
        .build();
  }

  @Override
  public RobotInstanceInjectRoot instanceRoot() {
    return _staticRoot.instanceRoot();
  }

  @Override
  public BeanContext lujbean() {
    return _lujbean;
  }

  StaticBeanCollector.Result _staticRoot;
  DynamicInitInvoker.Result _dynamicRoot;

  BeanContext _lujbean;
}
