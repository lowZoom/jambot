package luj.game.robot.internal.session;


import luj.ava.spring.Internal;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(basePackages = "luj.game.robot.internal",
    includeFilters = @ComponentScan.Filter(Internal.class),
    excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "luj\\.game\\.robot\\.internal\\.session\\..+"))
final class InjectConf {
  // NOOP
}
