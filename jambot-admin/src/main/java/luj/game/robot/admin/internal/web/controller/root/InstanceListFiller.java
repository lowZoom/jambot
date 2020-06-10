package luj.game.robot.admin.internal.web.controller.root;

import static java.util.stream.Collectors.toList;

import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import luj.game.robot.admin.internal.web.cluster.RequestSendAndWaiter;
import luj.game.robot.api.admin.message.InstanceListReq;
import luj.game.robot.api.admin.message.InstanceListRsp;
import org.springframework.ui.ModelMap;

public class InstanceListFiller {

  public InstanceListFiller(RequestSendAndWaiter reqSender, ModelMap out) {
    _reqSender = reqSender;
    _out = out;
  }

  public void fill() throws InterruptedException {
    InstanceListRsp rsp = _reqSender.sendAndWait(InstanceListReq.INSTANCE, InstanceListRsp.class);

    List<String> header = Arrays.stream(FIELDS)
        .map(f -> (String) f[0])
        .collect(toList());

    _out.addAttribute("table", ImmutableMap.of(
        "header", header,
        "body", makeBody(rsp.instanceList())
    ));
  }

  private List<?> makeBody(List<InstanceListRsp.Bot> instanceList) {
    List<F> fieldGetter = Arrays.stream(FIELDS)
        .map(f -> (F) f[1])
        .collect(toList());

    return instanceList.stream()
        .map(b -> fieldGetter.stream().map(g -> g.apply(b).toString()).collect(toList()))
        .collect(toList());
  }

  interface F extends Function<InstanceListRsp.Bot, Object> {
    // NOOP
  }

  private static final Object[][] FIELDS = {
      {"序号", (F) InstanceListRsp.Bot::index},
      {"已连接", (F) InstanceListRsp.Bot::connected},
  };

  private final RequestSendAndWaiter _reqSender;
  private final ModelMap _out;
}
