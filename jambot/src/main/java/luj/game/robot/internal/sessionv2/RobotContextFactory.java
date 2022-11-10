package luj.game.robot.internal.sessionv2;

import luj.game.robot.api.RobotContext;
import org.springframework.context.ApplicationContext;

public class RobotContextFactory {

  public RobotContextFactory(ApplicationContext appContext) {
    _appContext = appContext;
  }

  public RobotContext create() {
    return new RobotContextImpl(_appContext);
  }

  private final ApplicationContext _appContext;
}
