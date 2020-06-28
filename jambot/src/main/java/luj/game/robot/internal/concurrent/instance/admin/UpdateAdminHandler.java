package luj.game.robot.internal.concurrent.instance.admin;

import luj.ava.spring.Internal;
import luj.bean.api.BeanContext;
import luj.game.robot.internal.admin.message.internal.instance.UpdateInstanceMsg;
import luj.game.robot.internal.concurrent.instance.RobotInstanceActor;
import luj.game.robot.internal.start.botinstance.RobotState;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class UpdateAdminHandler implements RobotInstanceActor.Handler<UpdateAdminMsg> {

  @Override
  public void onHandle(Context ctx) {
    RobotInstanceActor self = ctx.getActorState(this);
    RobotState instance = self.getRobotState();

    self.getAdminRef().tell(_lujbean.createBean(UpdateInstanceMsg.class, (b, m) -> b
        .set(m::index, self.getIndex())
        .set(m::isConnected, instance.getConnection() != null)
        .set(m::status, instance.getStatus())
    ).getInstance());
  }

  @Autowired
  private BeanContext _lujbean;
}
