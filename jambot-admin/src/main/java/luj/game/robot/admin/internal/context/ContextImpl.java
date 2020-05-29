package luj.game.robot.admin.internal.context;

import java.util.Map;
import luj.game.robot.admin.api.AdminContext;
import luj.game.robot.admin.internal.start.AdminSpringStarter;
import org.springframework.context.ConfigurableApplicationContext;

final class ContextImpl implements AdminContext {

  ContextImpl(ConfigurableApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public void start(Map<String, Object> properties) {
    new AdminSpringStarter(_appContext, properties).start();
  }

  private final ConfigurableApplicationContext _appContext;
}
