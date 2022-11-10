package luj.game.robot.api.plugin;

import java.util.Collection;

public interface JambotDynamicInit {

  interface Context {

    <T> T getStartParam();

    void registerAll(Collection<?> beans);
  }

  void onInit(Context ctx);
}
