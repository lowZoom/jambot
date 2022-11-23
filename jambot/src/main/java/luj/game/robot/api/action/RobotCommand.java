package luj.game.robot.api.action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

public interface RobotCommand<P> {

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Component
  @interface Register {
    // NOOP
  }

  interface Context {

    <T> T getParam(RobotCommand<T> command);

    int getRobotIndex();

    <D> D getData(Class<D> dataType);

    Logger getLogger();

    void executeCommand(Class<? extends RobotCommand<?>> cmdType);

    Service service();
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
