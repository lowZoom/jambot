package luj.game.robot.admin.internal.web.cluster;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import luj.game.robot.admin.internal.cluster.JambotRef;
import luj.game.robot.admin.internal.cluster.request.actor.RequestWaitRefHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @see luj.game.robot.admin.internal.cluster.request.actor.RequestWaitActor
 */
@Component
public class RequestSendAndWaiter {

  @SuppressWarnings("unchecked")
  public <T> T sendAndWait(Object req, Class<T> rspType) throws InterruptedException {
    BlockingQueue<Object> recvQueue = _waitMap
        .computeIfAbsent(rspType, k -> new LinkedBlockingQueue<>());
    recvQueue.clear();

    _jambotRef.sendMessage(req, req.getClass(), _waitHolder.getActorRef());
    return (T) recvQueue.poll(5, TimeUnit.SECONDS);
  }

  public Map<Class<?>, BlockingQueue<Object>> getWaitMap() {
    return _waitMap;
  }

  /**
   * @see luj.game.robot.admin.internal.cluster.request.actor.AdminResponseHandler#onHandle
   */
  private final Map<Class<?>, BlockingQueue<Object>> _waitMap = new ConcurrentHashMap<>();

  @Autowired
  private JambotRef _jambotRef;

  @Autowired
  private RequestWaitRefHolder _waitHolder;
}
