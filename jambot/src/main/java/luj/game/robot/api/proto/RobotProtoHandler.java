package luj.game.robot.api.proto;

import luj.game.robot.api.action.RobotCommand;

public interface RobotProtoHandler<P> {

  interface Context {

    <P> P proto(RobotProtoHandler<P> handler);

    ///////////////////////

    @Deprecated
    void connect(String host, int port);

    void disconnect();

    void send(Object proto);

    ///////////////////////

    void putData(Object data);

    <D> D getData(Class<D> dataType);

    ///////////////////////

    void changeStatus(String status);

    void finishWait(Class<?> protoType);

    void executeCommand(Class<? extends RobotCommand> cmdType);
  }

  void onHandle(Context ctx);
}
