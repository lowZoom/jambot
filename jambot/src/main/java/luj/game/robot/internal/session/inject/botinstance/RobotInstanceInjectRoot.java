package luj.game.robot.internal.session.inject.botinstance;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.api.network.RobotDisconnectListener;
import org.springframework.beans.factory.annotation.Autowired;

public class RobotInstanceInjectRoot {

  public List<RobotCreateListener> getCreateListener() {
    return _createListener;
  }

  public RobotDisconnectListener getDisconnectListener() {
    return _disconnectListener;
  }

  @Autowired(required = false)
  List<RobotCreateListener> _createListener = ImmutableList.of();

//  @Autowired
  RobotDisconnectListener _disconnectListener;
}
