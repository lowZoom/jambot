package luj.game.robot.internal.net.disconnect;

import luj.cluster.api.actor.Tellable;
import luj.game.robot.api.network.RobotDisconnectListener;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.session.inject.botinstance.RobotInstanceInjectRoot;

public class BotDisconnectInvoker {

  public BotDisconnectInvoker(RobotInstanceDependency instanceDep, Tellable instanceRef) {
    _instanceDep = instanceDep;
    _instanceRef = instanceRef;
  }

  public void invoke() {
    RobotInstanceInjectRoot injectRoot = _instanceDep.getInjectRoot();
    RobotDisconnectListener listener = injectRoot.getDisconnectListener();

    ContextImpl ctx = new ContextImpl();
    ctx._instanceRef = _instanceRef;
    ctx._lujbean = _instanceDep.getLujbean();

    listener.onDisconnect(ctx);
  }

  private final RobotInstanceDependency _instanceDep;

  private final Tellable _instanceRef;
}
