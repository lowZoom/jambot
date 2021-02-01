package luj.game.robot.admin.internal.web.controller.root;

import static java.util.stream.Collectors.toList;
import static luj.game.robot.admin.internal.web.cluster.VerticalTableRenderer.f;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import luj.game.robot.admin.internal.web.cluster.RequestSendAndWaiter;
import luj.game.robot.admin.internal.web.cluster.VerticalTableRenderer;
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

    _out.addAttribute("table", new VerticalTableRenderer(ImmutableMap.of(
        "table.header", header,
        "table.body", makeBody(rsp.instanceList())
    ), ImmutableMap.of(), JUMP).render());
  }

  private List<?> makeBody(List<InstanceListRsp.Bot> instanceList) {
    List<F> fieldGetter = Arrays.stream(FIELDS)
        .map(f -> (F) f[1])
        .collect(toList());

    return instanceList.stream()
        .map(b -> encodeRow(b, fieldGetter))
        .collect(toList());
  }

  private List<?> encodeRow(InstanceListRsp.Bot bot, List<F> fieldGetter) {
    List<?> dataList = fieldGetter.stream()
        .map(g -> String.valueOf(g.apply(bot)))
        .collect(toList());

    List<?> opList = ImmutableList.of(
//        makeOp("呵呵", InstanceListFiller.class, ImmutableMap.of())
    );

    return ImmutableList.builder()
        .addAll(dataList)
        .addAll(opList)
        .build();
  }

  private interface F extends Function<InstanceListRsp.Bot, Object> {
    // NOOP
  }

  private static final Object[][] FIELDS = {
      {"序号", (F) InstanceListRsp.Bot::index},
      {"已连接", (F) InstanceListRsp.Bot::connected},
      {"状态", (F) InstanceListRsp.Bot::status},
  };


  private static final Object[][] JUMP = {
      {"详情", f(a -> ".")},
  };

  private final RequestSendAndWaiter _reqSender;

  private final ModelMap _out;
}
