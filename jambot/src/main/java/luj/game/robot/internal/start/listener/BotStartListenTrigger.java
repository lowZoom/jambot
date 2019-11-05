package luj.game.robot.internal.start.listener;

import java.util.List;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.net.api.NetContext;

public class BotStartListenTrigger {

  public BotStartListenTrigger(List<RobotStartListener> listenerList,
      NetContext lujnet, RobotProtoEncoder protoEncoder) {
    _listenerList = listenerList;
    _lujnet = lujnet;
    _protoEncoder = protoEncoder;
  }

  public void trigger() {
    if (_listenerList == null) {
      return;
    }

    StartContextImpl ctx = new StartContextImpl(_lujnet, _protoEncoder);
    for (RobotStartListener listener : _listenerList) {
      listener.onStart(ctx);
    }
  }

  private final List<RobotStartListener> _listenerList;

  private final NetContext _lujnet;
  private final RobotProtoEncoder _protoEncoder;
}
