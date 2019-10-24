package luj.game.robot.api.boot;

public interface RobotStartListener {

  interface Context {

    void connect(String host, int port);
  }

  void onStart(Context ctx);
}
