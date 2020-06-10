package luj.game.robot.internal.start.listener;

import luj.cluster.api.node.NodeStartListener;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.internal.instance.action.BotAction;
import luj.game.robot.internal.start.botinstance.BotInstanceCreator;

final class StartContextImpl implements RobotStartListener.Context {

  StartContextImpl(NodeStartListener.Actor parentRef) {
    _parentRef = parentRef;
  }

  @Override
  public void createRobot(String robotId) {
    new BotInstanceCreator(robotId, null, _parentRef).create();
  }

  @Override
  public void createRobot(BotAction action) {
    new BotInstanceCreator(null, action, _parentRef).create();
  }

  private final NodeStartListener.Actor _parentRef;
}
