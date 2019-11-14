package luj.game.robot.api.action;

public interface RobotCommand {

  interface Context {

    void send(Object proto);

    <D> D getData(Class<D> dataType);

    void executeCommand(Class<? extends RobotCommand> cmdType);
  }

  void onExecute(Context ctx);
}
