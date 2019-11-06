package luj.game.robot.api.boot;

public interface RobotStartListener {

  interface Context {

    Robot createRobot(String host, int port);
  }

  interface Robot {

    void send(Object proto);

    void putData(Object data);
  }

  void onStart(Context ctx);
}
