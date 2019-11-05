package luj.game.robot.api.proto;

public interface RobotProtoHandler<P> {

  interface Context {

    <P> P proto(RobotProtoHandler<P> handler);

    void connect(String host, int port);

    void send(Object proto);
  }

  void onHandle(Context ctx);
}
