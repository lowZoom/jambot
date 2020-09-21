package luj.game.robot.internal.instance.tick.step

import spock.lang.Specification


class NextStepGetterTest extends Specification {

  List _actionList
  int _oldActionIndex
  int _oldStepIndex

  void setup() {
    _actionList = [
        [6],
        [2],
    ]
  }

  def "GetNext:同一动作下一步"() {
    given:
    _oldActionIndex = 0
    _oldStepIndex = 0

    when:
    def result = getNext()

    then:
    result.actionIndex() == 0
    result.stepIndex() == 1
  }

  def "GetNext:下一个动作"() {
    given:
    _oldActionIndex = 0
    _oldStepIndex = 5

    when:
    def result = getNext()

    then:
    result.actionIndex() == 1
    result.stepIndex() == 0
  }

  def "GetNext:最后一步下一步"() {
    given:
    _oldActionIndex = 1
    _oldStepIndex = 1

    when:
    def result = getNext()

    then:
    result.actionIndex() == 0
    result.stepIndex() == 0
  }

  def "GetNext:最后一步下一步_只有一个动作和一步"() {
    given:
    _actionList = [[1]]
    _oldActionIndex = 0
    _oldStepIndex = 0

    when:
    def result = getNext()

    then:
    result.actionIndex() == 0
    result.stepIndex() == 0
  }

  NextStepGetter.Result getNext() {
    def actionList = _actionList.collect { List l ->
      def result = Stub(NextStepGetter.Action)
      result.getStepCount() >> { l[0] }
      return result
    }
    return new NextStepGetter(actionList, _oldActionIndex, _oldStepIndex).getNext()
  }
}
