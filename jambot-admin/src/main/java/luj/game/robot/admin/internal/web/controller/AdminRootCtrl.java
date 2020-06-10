package luj.game.robot.admin.internal.web.controller;

import javax.servlet.http.HttpServletRequest;
import luj.game.robot.admin.internal.web.cluster.RequestSendAndWaiter;
import luj.game.robot.admin.internal.web.controller.root.AdminRootFiller;
import luj.game.robot.admin.internal.web.controller.root.InstanceListFiller;
import org.springframework.beans.factory.annotation.Autowired;
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
  String index(ModelMap out) throws Exception {
    new AdminRootFiller(_reqSendAndWaiter, out).fill();
    return ftlPath("index");
  }

  @RequestMapping("list")
  String list(ModelMap out) throws Exception {
    new InstanceListFiller(_reqSendAndWaiter, out).fill();
    return ftlPath("table_vertical");
  }

  private String ftlPath(String fileName) {
    return "root/" + fileName;
  }

  @Autowired
  private RequestSendAndWaiter _reqSendAndWaiter;
}
