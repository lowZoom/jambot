package luj.game.robot.admin.internal.start;

import java.util.Map;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "luj.game.robot.admin.internal.web.controller")
public class AdminSpringStarter {

  public AdminSpringStarter(ConfigurableApplicationContext appContext, Map<String, Object> props) {
    _appContext = appContext;
    _props = props;
  }

  public void start() {
    BeanNameGenerator nameGen = _appContext.getBean(BeanNameGenerator.class);

    new SpringApplicationBuilder(AdminSpringStarter.class)
        .parent(_appContext)
        .beanNameGenerator(nameGen)
        .properties(_props)
        .run();
  }

  private final ConfigurableApplicationContext _appContext;

  //TODO: 指定端口
  private final Map<String, Object> _props;
}
