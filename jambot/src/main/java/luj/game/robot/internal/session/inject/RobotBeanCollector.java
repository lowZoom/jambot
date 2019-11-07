package luj.game.robot.internal.session.inject;

import java.util.List;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RobotBeanCollector {

  public interface Result {

    List<RobotStartListener> getStartListeners();

    RobotProtoEncoder getProtoEncoder();

    RobotProtoDecoder getProtoDecoder();

    List<RobotProtoHandler<?>> getProtoHandlerList();

    ///////////////

    RobotInstanceInjectRoot getInstanceInjectRoot();

    List<RobotCommand> getCommandList();
  }

  public RobotBeanCollector(ApplicationContext appContext) {
    _appContext = appContext;
  }

  public Result collect() {
    try (AnnotationConfigApplicationContext resultCtx = new AnnotationConfigApplicationContext()) {
      resultCtx.setParent(_appContext);

      resultCtx.register(InjectConf.class);
      resultCtx.refresh();

      return resultCtx.getBean(InjectRoot.class);
    }
  }

  private final ApplicationContext _appContext;
}
