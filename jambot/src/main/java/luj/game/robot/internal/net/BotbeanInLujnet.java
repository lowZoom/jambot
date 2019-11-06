package luj.game.robot.internal.net;

import java.util.Map;
import luj.game.robot.api.proto.RobotProtoDecoder;
import luj.game.robot.api.proto.RobotProtoHandler;

public class BotbeanInLujnet {

  public BotbeanInLujnet(RobotProtoDecoder protoDecoder,
      Map<Class<?>, RobotProtoHandler<?>> handlerMap) {
    _protoDecoder = protoDecoder;
    _handlerMap = handlerMap;
  }

  public RobotProtoDecoder getProtoDecoder() {
    return _protoDecoder;
  }

  public Map<Class<?>, RobotProtoHandler<?>> getHandlerMap() {
    return _handlerMap;
  }

  private final RobotProtoDecoder _protoDecoder;

  private final Map<Class<?>, RobotProtoHandler<?>> _handlerMap;
}
