package luj.game.robot.api.action;

public interface RobotCreateListener {

  interface Context {

    String getRobotId();

    void putData(Object data);

    void connect(String host, int port);

    void send(Object proto);
  }

  void onCreate(Context ctx);
}
