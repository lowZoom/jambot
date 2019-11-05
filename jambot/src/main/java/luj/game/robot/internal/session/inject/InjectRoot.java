package luj.game.robot.internal.session.inject;


import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.ava.spring.Internal;
import luj.game.robot.api.boot.RobotStartListener;
import luj.game.robot.api.proto.RobotProtoEncoder;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class InjectRoot implements RobotBeanCollector.Result {

  @Override
  public List<RobotStartListener> getStartListeners() {
    return nonNull(_startListeners);
  }

  @Override
  public RobotProtoEncoder getProtoEncoder() {
    return _protoEncoder;
  }

  private <T> List<T> nonNull(List<T> list) {
    return MoreObjects.firstNonNull(list, ImmutableList.of());
  }

  @Autowired(required = false)
  private List<RobotStartListener> _startListeners;

  @Autowired
  private RobotProtoEncoder _protoEncoder;
}
