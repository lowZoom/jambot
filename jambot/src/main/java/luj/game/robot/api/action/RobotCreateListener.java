package luj.game.robot.api.action;

public interface RobotCreateListener {

  interface Context {

    void putData(Object data);

    void connect(String host, int port);

    void send(Object proto);
  }

  void onCreate(Context ctx);
}
