package luj.game.robot.internal.sessionv2;

import akka.actor.ActorRef;
import java.util.function.Function;
import luj.cluster.api.node.ClusterNode;
import luj.game.robot.api.RobotContext;
import luj.game.robot.internal.session.RobotSessionStarter;
import luj.game.robot.internal.session.RobotSessionStarterV2;
import org.springframework.context.ApplicationContext;

final class RobotContextImpl implements RobotContext {

  RobotContextImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public void start(String host, int port) {
    _clusterNode = new RobotSessionStarter(host, port, _appContext).start();
  }

  @Override
  public void start(Function<Start, Start> config) {
    new RobotSessionStarterV2(_appContext).start();
  }

  @Override
  public void sendMessage(String msgKey, Object msg, ActorRef sender) {
    _clusterNode.sendMessage(msgKey, msg, sender);
  }

//  private static final Logger LOG = LoggerFactory.getLogger(RobotContextImpl.class);

  private ClusterNode _clusterNode;

  private final ApplicationContext _appContext;
}
