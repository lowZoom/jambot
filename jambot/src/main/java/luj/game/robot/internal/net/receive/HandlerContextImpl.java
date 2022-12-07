package luj.game.robot.internal.net.receive;

import luj.bean.api.BeanContext;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.game.robot.api.action.RobotDataCommand;
import luj.game.robot.api.proto.RobotProtoHandler;
import luj.game.robot.internal.concurrent.instance.command.CommandExecuteStarter;
import luj.game.robot.internal.instance.status.ChangeStatusRequestor;
import luj.game.robot.internal.start.botinstance.RobotState;

final class HandlerContextImpl implements RobotProtoHandler.Context {

  @SuppressWarnings("unchecked")
  @Override
  public <P> P proto(RobotProtoHandler<P> handler) {
    return (P) _proto;
  }

  @Override
  public void putData(Object data) {
    _robotState.getDataMap().put(data.getClass(), data);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <D> D getData(Class<D> dataType) {
    return (D) _robotState.getDataMap().get(dataType);
  }

  @Override
  public void changeStatus(String status) {
    new ChangeStatusRequestor(_instanceRef, status, _lujbean).request();
  }

  @Override
  public void finishWait(Class<?> protoType) {
    _robotState.getReceiveHistory().offer(protoType);
  }

  @Override
  public void executeCommand(Class<? extends RobotDataCommand> cmdType) {
    new CommandExecuteStarter(_instanceRef, cmdType).start();
  }

  Object _proto;
  RobotState _robotState;

  ActorMessageHandler.Ref _instanceRef;
  BeanContext _lujbean;
}
