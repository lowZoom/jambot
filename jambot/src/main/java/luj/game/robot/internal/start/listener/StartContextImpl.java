package luj.game.robot.internal.start.listener;

import java.util.List;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.internal.instance.config.BotConf;
import luj.game.robot.internal.start.botgroup.BotGroupCreator;

final class StartContextImpl implements RobotStartListener.Context {

  @Override
  public void createRobot(List<BotConf> conf) {
    for (BotConf c : conf) {
      new BotGroupCreator(c, _parentRef).create();
    }
  }

//  @Override
//  public void createRobot(String group, int amount) {
//    System.out.println("TODO: 创建机器人");
//  }

  Tellable _parentRef;
}
