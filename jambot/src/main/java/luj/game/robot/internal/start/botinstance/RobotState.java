package luj.game.robot.internal.start.botinstance;

import java.util.Map;
import java.util.Queue;
import luj.game.robot.internal.instance.config.StatusConf;


//TODO: 换用record
public class RobotState {

  public Map<Class<?>, Object> getDataMap() {
    return _dataMap;
  }

  public void setDataMap(Map<Class<?>, Object> dataMap) {
    _dataMap = dataMap;
  }

  public String getStatus() {
    return _status;
  }

  public void setStatus(String status) {
    _status = status;
  }

  public Map<String, StatusConf> getStatusMap() {
    return _statusMap;
  }

  public void setStatusMap(Map<String, StatusConf> statusMap) {
    _statusMap = statusMap;
  }

  public int getActionIndex() {
    return _actionIndex;
  }

  public void setActionIndex(int actionIndex) {
    _actionIndex = actionIndex;
  }

  public void setStepIndex(int stepIndex) {
    _stepIndex = stepIndex;
  }

  public int getStepIndex() {
    return _stepIndex;
  }

  public BotCurrentStep getCurStep() {
    return _curStep;
  }

  public void setCurStep(BotCurrentStep curStep) {
    _curStep = curStep;
  }

  public Queue<Class<?>> getReceiveHistory() {
    return _receiveHistory;
  }

  public void setReceiveHistory(Queue<Class<?>> receiveHistory) {
    _receiveHistory = receiveHistory;
  }

  private Map<Class<?>, Object> _dataMap;

  private String _status;
  private Map<String, StatusConf> _statusMap;

  private int _actionIndex;
  private int _stepIndex;
  private BotCurrentStep _curStep;

  private Queue<Class<?>> _receiveHistory;
}
