package luj.game.robot.admin.internal.cluster.request.actor;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import luj.ava.spring.Internal;
import luj.game.robot.internal.admin.actor.AdminResponseMsg;

@Internal
final class AdminResponseHandler implements RequestWaitActor.Handler<AdminResponseMsg> {

  @Override
  public void onHandle(Context ctx) {
    RequestWaitActor self = ctx.getActorState(this);
    AdminResponseMsg msg = ctx.getMessage(this);

    Map<Class<?>, BlockingQueue<Object>> waitMap = self.getWaitMap();
    BlockingQueue<Object> queue = waitMap.get(msg.responseType());

    queue.offer(msg.responseData());
  }
}
