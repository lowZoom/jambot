package luj.game.robot.api.action;

public interface RobotCommand {

  interface Context {

    void send(Object proto);

    <D> D getData(Class<D> dataType);
  }

  void onExecute(Context ctx);
}
