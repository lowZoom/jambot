package luj.game.robot.internal.session;

import luj.bean.api.BeanContext;
import luj.bean.api.LujBean;
import luj.cluster.api.LujCluster;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.internal.dynamic.combine.AllBeanCombiner;
import luj.game.robot.internal.dynamic.init.DynamicInitInvoker;
import luj.game.robot.internal.session.inject.StaticBeanCollector;
import luj.game.robot.internal.sessionv2.plugin.start.BootInitInvoker;
import luj.game.robot.internal.start.BotbeanInLujcluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RobotSessionStarterV2 {

  public RobotSessionStarterV2(ApplicationContext appContext) {
    _appContext = appContext;
  }

  public void start() {
    StaticBeanCollector.Result staticRoot = new StaticBeanCollector(_appContext).collect();
    BootInitInvoker.Result bootCfg = new BootInitInvoker(staticRoot.getBootInitPlugin()).invoke();

    DynamicInitInvoker.Result dynamicRoot = new DynamicInitInvoker(
        staticRoot.getDynamicInitPlugin(), bootCfg.startParam()).invoke();

    BeanContext lujbean = LujBean.start();
    AllBeanCombiner.Result allRoot = new AllBeanCombiner(
        staticRoot, dynamicRoot, lujbean).combine();

    if (allRoot.startListener().isEmpty()) {
      LOG.warn("没有启动逻辑，机器人结束：{}", RobotStartListener.class.getName());
      return;
    }

    try (var internalCtx = new AnnotationConfigApplicationContext()) {
      internalCtx.registerBean(BeanContext.class, () -> lujbean);

      internalCtx.register(InjectConf.class);
      internalCtx.refresh();

      startLujcluster(internalCtx, allRoot);
    }
  }

  /**
   * @see luj.game.robot.internal.start.OnLujclusterStart
   */
  private void startLujcluster(ApplicationContext botCtx, AllBeanCombiner.Result injectRoot) {
    var botbean = new BotbeanInLujcluster(injectRoot, injectRoot.lujbean());

//    List<String> seeds = ImmutableList.of(_host + ":" + _port);
    LujCluster.start(botCtx).startNode(c -> c
        .startParam(botbean)
    );
  }

  private static final Logger LOG = LoggerFactory.getLogger(RobotSessionStarterV2.class);

  private final ApplicationContext _appContext;
}
