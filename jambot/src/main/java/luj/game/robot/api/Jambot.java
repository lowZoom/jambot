package luj.game.robot.api;

import luj.game.robot.internal.session.RobotSessionStarterV2;
import org.springframework.context.ApplicationContext;

public enum Jambot {
  ;

  public static void start(ApplicationContext appContext) {
    new RobotSessionStarterV2(appContext).start();
  }
}
