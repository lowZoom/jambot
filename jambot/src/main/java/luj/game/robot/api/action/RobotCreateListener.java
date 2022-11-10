package luj.game.robot.api.action;

public interface RobotCreateListener {

  interface Context {

    void putData(Object data);

    @Deprecated
    void connect(String host, int port);

    @Deprecated
    void send(Object proto);
  }

  void onCreate(Context ctx);
}
