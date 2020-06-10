package luj.game.robot.admin.internal.web.controller.root;

import luj.game.robot.admin.internal.web.cluster.RequestSendAndWaiter;
import luj.game.robot.api.admin.message.AdminOverviewReq;
import luj.game.robot.api.admin.message.AdminOverviewRsp;
import org.springframework.ui.ModelMap;

public class AdminRootFiller {

  public AdminRootFiller(RequestSendAndWaiter reqSender, ModelMap out) {
    _reqSender = reqSender;
    _out = out;
  }

  public void fill() throws InterruptedException {
    AdminOverviewRsp rsp = _reqSender.sendAndWait(
        AdminOverviewReq.INSTANCE, AdminOverviewRsp.class);

//    LOG.debug("机器人数量：{}", rsp.robotCount());
    _out.addAttribute("robotCount", rsp.robotCount());
  }

//  private static final Logger LOG = LoggerFactory.getLogger(AdminRootFiller.class);

  private final RequestSendAndWaiter _reqSender;
  private final ModelMap _out;
}
