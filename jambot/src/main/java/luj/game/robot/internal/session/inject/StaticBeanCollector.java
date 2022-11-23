package luj.game.robot.internal.session.inject;

import java.util.List;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.plugin.JambotBootInit;
import luj.game.robot.api.plugin.JambotDynamicInit;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StaticBeanCollector {

  public interface Result {

    List<RobotStartListener> getStartListeners();

    RobotProtoEncoder getProtoEncoder();

    RobotProtoDecoder getProtoDecoder();

    List<RobotProtoHandler<?>> getProtoHandlerList();

    ///////////////

    RobotInstanceInjectRoot getInstanceInjectRoot();

    List<RobotCommand<?>> getCommandList();

    JambotBootInit getBootInitPlugin();

    JambotDynamicInit getDynamicInitPlugin();
  }

  public StaticBeanCollector(ApplicationContext appContext) {
    _appContext = appContext;
  }

  public Result collect() {
    try (AnnotationConfigApplicationContext internalCtx = new AnnotationConfigApplicationContext()) {
      internalCtx.setParent(_appContext);

      internalCtx.register(InjectConf.class);
      internalCtx.refresh();

      return internalCtx.getBean(InjectRoot.class);
    }
  }

  private final ApplicationContext _appContext;
}
