package luj.game.robot.admin.internal.cluster.request.actor;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.actor.ActorPreStartHandler;

public class RequestWaitActor {

  public interface Handler<M> extends ActorMessageHandler<RequestWaitActor, M> {
    // NOOP
  }

  public interface PreStart extends ActorPreStartHandler<RequestWaitActor> {
    // NOOP
  }

  public Map<Class<?>, BlockingQueue<Object>> getWaitMap() {
    return _waitMap;
  }

  public void setWaitMap(Map<Class<?>, BlockingQueue<Object>> waitMap) {
    _waitMap = waitMap;
  }

  public RequestWaitRefHolder getWaitHolder() {
    return _waitHolder;
  }

  public void setWaitHolder(RequestWaitRefHolder waitHolder) {
    _waitHolder = waitHolder;
  }

  /**
   * @see luj.game.robot.admin.internal.web.cluster.RequestSendAndWaiter#_waitMap
   */
  private Map<Class<?>, BlockingQueue<Object>> _waitMap;

  private RequestWaitRefHolder _waitHolder;
}
