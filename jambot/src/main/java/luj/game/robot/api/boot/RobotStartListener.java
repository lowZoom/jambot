package luj.game.robot.api.boot;

import java.util.List;
import luj.game.robot.internal.instance.config.BotConf;

public interface RobotStartListener {

  interface Context {

    void createRobot(List<BotConf> conf);
  }

  void onStart(Context ctx);
}
