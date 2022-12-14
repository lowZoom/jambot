package luj.game.robot.api.network;

import luj.game.robot.api.action.RobotDataCommand;

public interface RobotProtoHandler<P> {

  interface Context {

    <P> P proto(RobotProtoHandler<P> handler);

    ///////////////////////

    void putData(Object data);

    <D> D getData(Class<D> dataType);

    ///////////////////////

    void changeStatus(String status);

    void finishWait(Class<?> protoType);

    @Deprecated
    void executeCommand(Class<? extends RobotDataCommand> cmdType);
  }

  void onHandle(Context ctx);
}
