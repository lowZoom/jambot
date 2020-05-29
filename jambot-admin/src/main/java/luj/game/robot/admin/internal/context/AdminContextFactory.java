package luj.game.robot.admin.internal.context;

import luj.game.robot.admin.api.AdminContext;
import org.springframework.context.ConfigurableApplicationContext;

public class AdminContextFactory {

  public AdminContextFactory(ConfigurableApplicationContext appContext) {
    _appContext = appContext;
  }

  public AdminContext create() {
    return new ContextImpl(_appContext);
  }

  private final ConfigurableApplicationContext _appContext;
}
