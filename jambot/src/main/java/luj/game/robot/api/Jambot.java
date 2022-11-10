package luj.game.robot.api;

import luj.game.robot.internal.sessionv2.RobotContextFactory;
import org.springframework.context.ApplicationContext;

public enum Jambot {
  ;

  public static RobotContext start(ApplicationContext appContext) {
    return new RobotContextFactory(appContext).create();
  }
}
