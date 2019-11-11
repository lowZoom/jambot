package luj.game.robot.internal.session.inject.botinstance;

import luj.ava.spring.Internal;
import luj.game.robot.api.action.RobotCreateListener;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoEncoder;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
public class RobotInstanceInjectRoot {

  public RobotCreateListener getCreateListener() {
    return _createListener;
  }

  public RobotProtoEncoder getProtoEncoder() {
    return _protoEncoder;
  }

  public RobotProtoDecoder getProtoDecoder() {
    return _protoDecoder;
  }

  @Autowired
  private RobotCreateListener _createListener;

  @Autowired
  private RobotProtoEncoder _protoEncoder;

  @Autowired
  private RobotProtoDecoder _protoDecoder;
}
