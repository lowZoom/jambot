package luj.game.robot.internal.start.botgroup;

import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.instance.config.BotConf;
import luj.game.robot.internal.start.botinstance.BotInstanceCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotGroupCreator {

  public BotGroupCreator(BotConf conf, Tellable parentRef) {
    _conf = conf;
    _parentRef = parentRef;
  }

  public void create() {
    int botAmount = _conf.getAmount();
    LOG.debug("创建机器人组：{}，数量：{}", _conf.getName(), botAmount);

    for (int i = 0; i < botAmount; i++) {
      new BotInstanceCreator(null,
          _conf.getInitStatus(), _conf.getStatusMap(), _parentRef).create();
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(BotGroupCreator.class);

  private final BotConf _conf;
  private final Tellable _parentRef;
}
