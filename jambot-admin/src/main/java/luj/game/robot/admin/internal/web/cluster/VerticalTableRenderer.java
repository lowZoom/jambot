package luj.game.robot.admin.internal.web.cluster;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VerticalTableRenderer {

  public static <A extends Map<String, ?>> Function<A, String> f(Function<A, String> f) {
    return f;
  }

  public VerticalTableRenderer(Map<String, List<?>> valueMap,
      Map<String, Function<Object, ?>> renderMap, Object[][] jumpList) {
    _valueMap = valueMap;
    _renderMap = renderMap;
    _jumpList = jumpList;
  }

  @SuppressWarnings("unchecked")
  public Map<String, List<?>> render() {
    List<String> header = (List<String>) _valueMap.get("table.header");

    List<Map<String, ?>> body = new BodyRowWithOpRenderer(
        (List<List<?>>) _valueMap.get("table.body"), header, _renderMap, makeJumpList()).render();

    return ImmutableMap.of(
        "header", header,
        "body", body
    );
  }

  private List<List<?>> makeJumpList() {
    return (_jumpList == null ? Stream.<Object[]>empty() : Arrays.stream(_jumpList))
        .map(ImmutableList::copyOf)
        .collect(Collectors.toList());
  }

  private final Map<String, List<?>> _valueMap;

  private final Map<String, Function<Object, ?>> _renderMap;
  private final Object[][] _jumpList;
}
