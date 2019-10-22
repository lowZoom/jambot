package luj.game.robot.api;

import luj.game.robot.internal.session.RobotSessionStarter;
import org.springframework.context.ApplicationContext;

public enum Jambot {
  ;

  public static void start(ApplicationContext appContext) {
    new RobotSessionStarter("192.168.188.227", 8190, appContext).start();
  }
}
