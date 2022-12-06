package luj.game.robot.api.network;

@Deprecated
public interface RobotDisconnectListener {

  interface Context {

    void changeStatus(String status);
  }

  void onDisconnect(Context ctx);
}
