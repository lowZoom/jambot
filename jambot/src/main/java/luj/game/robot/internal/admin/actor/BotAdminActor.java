package luj.game.robot.internal.admin.actor;

import java.util.LinkedHashMap;
import java.util.Map;
import luj.cluster.api.actor.ActorMessageHandler;

public class BotAdminActor {

  public interface Handler<M> extends ActorMessageHandler<BotAdminActor, M> {
    // NOOP
  }

  public Map<Integer, AdminBotInfo> getInstanceMap() {
    return _instanceMap;
  }

  private final Map<Integer, AdminBotInfo> _instanceMap = new LinkedHashMap<>();
}
