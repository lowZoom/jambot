package luj.game.robot.admin.internal.start;

import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "luj.game.robot.admin.internal")
public class AdminSpringStarter {

  public AdminSpringStarter(ApplicationContext appContext, String[] args) {
    _appContext = appContext;
    _args = args;
  }

  public void start() {
    BeanNameGenerator nameGen = _appContext.getBean(BeanNameGenerator.class);

    new SpringApplicationBuilder(AdminSpringStarter.class)
        .beanNameGenerator(nameGen)
        .run();
  }

  private final ApplicationContext _appContext;

  //TODO: 指定端口
  private final String[] _args;
}
