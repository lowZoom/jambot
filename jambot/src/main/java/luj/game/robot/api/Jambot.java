package luj.game.robot.api;

import luj.game.robot.internal.session.RobotSessionStarter;
import org.springframework.context.ApplicationContext;

public enum Jambot {
  ;

  public static void start(ApplicationContext appContext) {
    new RobotSessionStarter("127.0.0.1", 10608, appContext).start();
  }
}
