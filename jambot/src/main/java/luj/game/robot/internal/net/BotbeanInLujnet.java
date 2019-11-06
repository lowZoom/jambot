package luj.game.robot.internal.net;

import java.util.Map;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoEncoder;
import luj.game.robot.api.proto.RobotProtoHandler;

public class BotbeanInLujnet {

  public BotbeanInLujnet(RobotProtoEncoder protoEncoder, RobotProtoDecoder protoDecoder,
      Map<Class<?>, RobotProtoHandler<?>> handlerMap) {
    _protoEncoder = protoEncoder;
    _protoDecoder = protoDecoder;
    _handlerMap = handlerMap;
  }

  public RobotProtoEncoder getProtoEncoder() {
    return _protoEncoder;
  }

  public RobotProtoDecoder getProtoDecoder() {
    return _protoDecoder;
  }

  public Map<Class<?>, RobotProtoHandler<?>> getHandlerMap() {
    return _handlerMap;
  }

  private final RobotProtoEncoder _protoEncoder;
  private final RobotProtoDecoder _protoDecoder;

  private final Map<Class<?>, RobotProtoHandler<?>> _handlerMap;
}
