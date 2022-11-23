package luj.game.robot.internal.dynamic.combine;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.game.robot.api.action.RobotCommand;
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
  public List<RobotCommand<?>> command() {
    return ImmutableList.<RobotCommand<?>>builder()
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

  StaticBeanCollector.Result _staticRoot;

  DynamicInitInvoker.Result _dynamicRoot;
}
