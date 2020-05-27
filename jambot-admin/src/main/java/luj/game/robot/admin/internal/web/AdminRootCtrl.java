package luj.game.robot.admin.internal.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/jambot")
final class AdminRootCtrl {

  @RequestMapping({"/", ""})
  RedirectView indexRedir(HttpServletRequest request) {
    return new RedirectView(request.getRequestURI() + "/index");
  }

  @RequestMapping("index")
  String index(ModelMap out) {
    return ftlPath("index");
  }

  private String ftlPath(String fileName) {
    return "root/" + fileName;
  }
}
