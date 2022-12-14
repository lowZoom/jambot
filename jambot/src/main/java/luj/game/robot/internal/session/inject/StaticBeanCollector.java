package luj.game.robot.internal.session.inject;

import java.util.List;
import luj.game.robot.api.action.RobotDataCommand;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.plugin.JambotBootInit;
import luj.game.robot.api.plugin.JambotDynamicInit;
import luj.game.robot.api.network.RobotProtoHandler;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StaticBeanCollector {

  public interface Result {

    List<RobotStartListener> startListener();

    List<RobotDataCommand<?>> dataCommand();

    List<RobotProtoHandler<?>> protoHandler();

    RobotInstanceInjectRoot instanceRoot();

    ///////////////

    JambotBootInit getBootInitPlugin();

    JambotDynamicInit getDynamicInitPlugin();
  }

  public StaticBeanCollector(ApplicationContext externalContext) {
    _externalContext = externalContext;
  }

  public Result collect() {
    try (var resultCtx = new AnnotationConfigApplicationContext()) {
      resultCtx.setParent(_externalContext);

      resultCtx.register(RobotInstanceInjectRoot.class);
      resultCtx.register(InjectRoot.class);
      resultCtx.refresh();

      return resultCtx.getBean(InjectRoot.class);
    }
  }

  private final ApplicationContext _externalContext;
}
