package luj.game.robot.api.boot;

import luj.game.robot.internal.instance.action.BotAction;

public interface RobotStartListener {

  interface Context {

    void createRobot(String robotId);

    void createRobot(BotAction action);
  }

  void onStart(Context ctx);
}
