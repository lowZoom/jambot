package luj.game.robot.internal.session;


import luj.ava.spring.Internal;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
    basePackages = "luj.game.robot.internal.start",
    includeFilters = @ComponentScan.Filter(Internal.class))
final class InjectConf {
  // NOOP
}
