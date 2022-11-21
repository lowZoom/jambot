package luj.game.robot.api;

import luj.game.robot.internal.sessionv2.RobotContextFactory;
import org.springframework.context.ApplicationContext;

public enum Jambot {
  ;

  public static void start(ApplicationContext appContext) {
    new RobotContextFactory(appContext).create().start(null);
  }
}
