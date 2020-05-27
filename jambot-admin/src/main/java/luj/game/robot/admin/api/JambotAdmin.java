package luj.game.robot.admin.api;

import luj.game.robot.admin.internal.context.AdminContextFactory;
import org.springframework.context.ApplicationContext;

public enum JambotAdmin {
  ;

  public static AdminContext start(ApplicationContext appContext) {
    return new AdminContextFactory(appContext).create();
  }
}
