package luj.game.robot.admin.api;

import luj.game.robot.admin.internal.context.AdminContextFactory;
import org.springframework.context.ConfigurableApplicationContext;

public enum JambotAdmin {
  ;

  public static AdminContext start(ConfigurableApplicationContext appContext) {
    return new AdminContextFactory(appContext).create();
  }
}
