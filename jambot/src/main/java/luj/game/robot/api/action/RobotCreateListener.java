package luj.game.robot.api.action;

public interface RobotCreateListener {

  interface Context {

    void putData(Object data);
  }

  void onCreate(Context ctx);
}
