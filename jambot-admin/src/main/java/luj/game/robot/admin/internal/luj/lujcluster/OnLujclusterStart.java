package luj.game.robot.admin.internal.luj.lujcluster;

import luj.ava.spring.Internal;
import luj.cluster.api.node.NodeStartListener;
import luj.game.robot.admin.internal.cluster.request.actor.RequestWaitActor;
import luj.game.robot.admin.internal.cluster.request.actor.RequestWaitRefHolder;
import luj.game.robot.admin.internal.web.cluster.RequestSendAndWaiter;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class OnLujclusterStart implements NodeStartListener {

  @Override
  public void onStart(Context ctx) {
    RequestWaitActor actor = new RequestWaitActor();
    actor.setWaitMap(_requestSender.getWaitMap());
    actor.setWaitHolder(_waitHolder);

    ctx.createApplicationActor(actor);
  }

  @Autowired
  private RequestSendAndWaiter _requestSender;

  @Autowired
  private RequestWaitRefHolder _waitHolder;
}
