package luj.game.robot.internal.session.inject;

import java.util.List;
import luj.game.robot.api.boot.RobotStartListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RobotBeanCollector {

  public interface Result {

    List<RobotStartListener> getStartListeners();
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
