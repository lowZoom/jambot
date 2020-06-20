package luj.game.robot.internal.start.botgroup;

import luj.cluster.api.actor.Tellable;
import luj.game.robot.internal.instance.config.BotConf;
import luj.game.robot.internal.start.botinstance.BotInstanceCreator;

public class BotGroupCreator {

  public BotGroupCreator(BotConf conf, Tellable parentRef) {
    _conf = conf;
    _parentRef = parentRef;
  }

  public void create() {
    for (int i = 0; i < _conf.getAmount(); i++) {
      new BotInstanceCreator(null,
          _conf.getInitStatus(), _conf.getStatusMap(), _parentRef).create();
    }
  }

  private final BotConf _conf;

  private final Tellable _parentRef;
}
