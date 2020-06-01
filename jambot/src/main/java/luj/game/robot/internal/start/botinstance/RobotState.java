package luj.game.robot.internal.start.botinstance;

import java.util.Map;
import luj.game.robot.internal.instance.action.BotAction;
import luj.game.robot.internal.instance.action.step.ActionStep;
import luj.net.api.client.NetConnection;

public class RobotState {

  public NetConnection getConnection() {
    return _connection;
  }

  public void setConnection(NetConnection connection) {
    _connection = connection;
  }

  public Map<Class<?>, Object> getDataMap() {
    return _dataMap;
  }

  public void setDataMap(Map<Class<?>, Object> dataMap) {
    _dataMap = dataMap;
  }

  public void setCurAction(BotAction curAction) {
    _curAction = curAction;
  }

  public BotAction getCurAction() {
    return _curAction;
  }

  public void setCurStep(ActionStep curStep) {
    _curStep = curStep;
  }

  public ActionStep getCurStep() {
    return _curStep;
  }

  public void setStepIndex(int stepIndex) {
    _stepIndex = stepIndex;
  }

  public int getStepIndex() {
    return _stepIndex;
  }

  private NetConnection _connection;
  private Map<Class<?>, Object> _dataMap;

  private BotAction _curAction;
  private ActionStep _curStep;
  private int _stepIndex;
}
