package luj.game.robot.admin.internal.context;

import luj.game.robot.admin.api.AdminContext;
import org.springframework.context.ApplicationContext;

public class AdminContextFactory {

  public AdminContextFactory(ApplicationContext appContext) {
    _appContext = appContext;
  }

  public AdminContext create() {
    return new ContextImpl(_appContext);
  }

  private final ApplicationContext _appContext;
}
