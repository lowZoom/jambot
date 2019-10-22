package luj.game.robot.internal.session.inject;


import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.ava.spring.Internal;
import luj.game.robot.api.boot.RobotStartListener;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class InjectRoot implements RobotBeanCollector.Result {

  @Override
  public List<RobotStartListener> getStartListeners() {
    return nonnull(_startListeners);
  }

  private <T> List<T> nonnull(List<T> list) {
    return MoreObjects.firstNonNull(list, ImmutableList.of());
  }

  @Autowired(required = false)
  private List<RobotStartListener> _startListeners;
}
