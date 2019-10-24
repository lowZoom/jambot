package luj.game.robot.internal.session.inject;

import luj.ava.spring.Internal;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
    basePackages = "luj.game.robot.internal.session.inject",
    includeFilters = @ComponentScan.Filter(Internal.class))
final class InjectConf {
  // NOOP
}
