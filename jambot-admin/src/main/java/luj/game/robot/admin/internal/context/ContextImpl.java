package luj.game.robot.admin.internal.context;

import luj.game.robot.admin.api.AdminContext;
import luj.game.robot.admin.internal.start.AdminSpringStarter;
import org.springframework.context.ApplicationContext;

final class ContextImpl implements AdminContext {

  ContextImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public void start() {
    new AdminSpringStarter(_appContext, null).start();
  }

  private final ApplicationContext _appContext;
}
