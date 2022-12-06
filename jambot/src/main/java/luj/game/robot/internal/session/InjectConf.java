package luj.game.robot.internal.session;


import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
    "luj.game.robot.internal",
}, includeFilters = {
    @ComponentScan.Filter(luj.ava.spring.Internal.class),
    @ComponentScan.Filter(luj.spring.anno.Internal.class),
})
final class InjectConf {
  // NOOP
}
