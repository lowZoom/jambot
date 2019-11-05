package luj.game.robot.internal.start.listener;

import java.util.List;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoEncoder;

public class BotStartListenTrigger {

  public BotStartListenTrigger(List<RobotStartListener> listenerList,
      RobotProtoEncoder protoEncoder) {
    _listenerList = listenerList;
    _protoEncoder = protoEncoder;
  }

  public void trigger() {
    if (_listenerList == null) {
      return;
    }

    StartContextImpl ctx = new StartContextImpl(_protoEncoder);
    for (RobotStartListener listener : _listenerList) {
      listener.onStart(ctx);
    }
  }

  private final List<RobotStartListener> _listenerList;

  private final RobotProtoEncoder _protoEncoder;
}
