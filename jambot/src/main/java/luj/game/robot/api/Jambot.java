package luj.game.robot.api;

import luj.game.robot.internal.context.RobotContextFactory;
import org.springframework.context.ApplicationContext;

public enum Jambot {
  ;

  public static RobotContext start(ApplicationContext appContext) {
    return new RobotContextFactory(appContext).create();
  }
}
