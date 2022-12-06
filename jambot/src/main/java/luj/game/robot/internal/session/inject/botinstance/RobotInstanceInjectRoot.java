package luj.game.robot.internal.session.inject.botinstance;

import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.api.network.RobotDisconnectListener;

public class RobotInstanceInjectRoot {

  public RobotCreateListener getCreateListener() {
    return _createListener;
  }

  public RobotDisconnectListener getDisconnectListener() {
    return _disconnectListener;
  }

//  @Autowired
  private RobotCreateListener _createListener;

//  @Autowired
  private RobotDisconnectListener _disconnectListener;
}
