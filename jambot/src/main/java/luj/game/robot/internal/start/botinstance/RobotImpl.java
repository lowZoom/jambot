package luj.game.robot.internal.start.botinstance;

import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.internal.net.send.BotProtoSender;

final class RobotImpl implements RobotStartListener.Robot {

  RobotImpl(RobotState robotState, RobotProtoEncoder protoEncoder) {
    _robotState = robotState;
    _protoEncoder = protoEncoder;
  }

  @Override
  public void send(Object proto) {
    new BotProtoSender(proto, _protoEncoder, _robotState.getConnection()).send();
  }

  @Override
  public void putData(Object data) {
    _robotState.getDataMap().put(data.getClass(), data);
  }

  private final RobotState _robotState;

  private final RobotProtoEncoder _protoEncoder;
}
