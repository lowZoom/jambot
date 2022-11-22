package luj.game.robot.internal.start.listener;

import java.util.List;
import luj.cluster.api.actor.Tellable;
import luj.cluster.api.node.NodeStartListener;
import luj.game.robot.api.boot.RobotStartListener;

public class BotStartListenTrigger {

  public BotStartListenTrigger(NodeStartListener.Actor parentRef,
      List<RobotStartListener> listenerList) {
    _parentRef = parentRef;
    _listenerList = listenerList;
  }

  public void trigger() {
    StartContextImpl ctx = new StartContextImpl();
    ctx._parentRef = _parentRef;

    for (RobotStartListener listener : _listenerList) {
      listener.onStart(ctx);
    }
  }

  private final Tellable _parentRef;

  private final List<RobotStartListener> _listenerList;
}
