package luj.game.robot.internal.session;


import luj.ava.spring.Internal;
import luj.bean.api.BeanContext;
import luj.bean.api.LujBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "luj.game.robot.internal",
    includeFilters = @ComponentScan.Filter(Internal.class),
    excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "luj\\.game\\.robot\\.internal\\.session\\..+"))
class InjectConf {

  @Bean
  BeanContext lujbean() {
    return LujBean.start();
  }
}
