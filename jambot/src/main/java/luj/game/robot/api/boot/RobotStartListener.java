package luj.game.robot.api.boot;

public interface RobotStartListener {

  interface Context {

    void createRobot(String robotId);
  }

  void onStart(Context ctx);
}
