package luj.game.robot.internal.session;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import luj.ava.reflect.type.TypeX;
import luj.cluster.api.LujCluster;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.net.BotbeanInLujnet;
import luj.game.robot.internal.session.inject.RobotBeanCollector;
import luj.game.robot.internal.start.BotbeanInLujcluster;
import luj.net.api.LujNet;
import luj.net.api.NetContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RobotSessionStarter {

  public RobotSessionStarter(String host, int port, ApplicationContext appContext) {
    _host = host;
    _port = port;
    _appContext = appContext;
  }

  public void start() {
    RobotBeanCollector.Result root = new RobotBeanCollector(_appContext).collect();

    List<RobotStartListener> listenerList = root.getStartListeners();
    if (listenerList.isEmpty()) {
      LOG.warn("没有启动逻辑，机器人结束：{}", RobotStartListener.class.getName());
      return;
    }

    try (AnnotationConfigApplicationContext botCtx =
        new AnnotationConfigApplicationContext(InjectConf.class)) {
      startLujcluster(botCtx, root);
    }
  }

  private void startLujcluster(ApplicationContext botCtx, RobotBeanCollector.Result injectRoot) {
    NetContext lujnet = LujNet.create(botCtx, createNetParam(injectRoot));
    BotbeanInLujcluster botbean = new BotbeanInLujcluster(injectRoot, lujnet);

    LujCluster.start(botCtx).startNode(_host, _port, _host + ":" + _port, botbean);
  }

  private BotbeanInLujnet createNetParam(RobotBeanCollector.Result injectRoot) {
    Map<Class<?>, RobotProtoHandler<?>> handleMap = injectRoot.getProtoHandlerList().stream()
        .collect(Collectors.toMap(this::getProtoType, Function.identity()));

    return new BotbeanInLujnet(injectRoot.getProtoDecoder(), handleMap);
  }

  private Class<?> getProtoType(RobotProtoHandler<?> handler) {
    return TypeX.ofInstance(handler)
        .getSupertype(RobotProtoHandler.class)
        .getTypeParam(0)
        .asClass();
  }

  private static final Logger LOG = LoggerFactory.getLogger(RobotSessionStarter.class);

  private final String _host;
  private final int _port;

  private final ApplicationContext _appContext;
}
