package luj.game.robot.api.network;

public interface RobotDisconnectListener {

  interface Context {

    void changeStatus(String status);
  }

  void onDisconnect(Context ctx);
}
