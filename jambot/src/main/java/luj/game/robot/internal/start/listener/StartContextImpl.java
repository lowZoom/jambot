package luj.game.robot.internal.start.listener;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.internal.instance.config.BotConf;
import luj.game.robot.internal.start.botgroup.BotGroupCreator;
import luj.game.robot.internal.start.botinstance.BotInstanceCreator;

final class StartContextImpl implements RobotStartListener.Context {

  StartContextImpl(Tellable parentRef) {
    _parentRef = parentRef;
  }

  @Override
  public void createRobot(String robotId) {
    new BotInstanceCreator(robotId, null, ImmutableMap.of(), 9, 9, _parentRef).create();
  }

  @Override
  public void createRobot(List<BotConf> conf) {
    for (BotConf c : conf) {
      new BotGroupCreator(c, _parentRef).create();
    }
  }

  private final Tellable _parentRef;
}
