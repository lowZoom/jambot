package luj.game.robot.api.action;

public interface RobotCreateListener {

  interface Context {

    void putData(Object data);

    Internal getInternal();
  }

  interface Internal {

    Network network();
  }

  interface Network {

    void receiveProto(String protoKey, Object protoObj, Object param);
  }

  void onCreate(Context ctx);
}
