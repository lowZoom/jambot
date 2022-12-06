package luj.game.robot.api.action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

public interface RobotDataCommand<P> {

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Component
  @interface Register {
    // NOOP
  }

  interface Context {

    <T> T param(RobotDataCommand<T> command);

    Robot robot();

    Logger logger();

    Service service();
  }

  interface Robot {

    void putData(Object data);

    <D> D getData(Class<D> dataType);

    <P> CommandService<P> command(Class<? extends RobotDataCommand<P>> cmdType);
  }

  interface Service {

    Random random();
  }

  interface Random {

    int randInt(int minInclude, int maxExclude);

    boolean randBool(double likelihood);

    <T> T randElement(Collection<T> collection);
  }

  void onExecute(Context ctx);
}
