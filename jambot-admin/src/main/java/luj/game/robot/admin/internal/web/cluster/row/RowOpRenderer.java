package luj.game.robot.admin.internal.web.cluster.row;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RowOpRenderer {

  public RowOpRenderer(List<Map<String, ?>> opList) {
    _opList = opList;
  }

  public List<Map<String, String>> render() {
    return _opList.stream()
        .map(this::renderCell)
        .collect(Collectors.toList());
  }

  private Map<String, String> renderCell(Map<String, ?> opMap) {
    return new ValueMapRenderer(opMap, ImmutableMap
        .of("param", (Function<Object, ?>) this::toUrlStr)).render();
  }

  private String toUrlStr(Object param) {
    try {
      return URLEncoder.encode(JACKSON.writeValueAsString(param), StandardCharsets.UTF_8.name());

    } catch (Exception e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private static final ObjectMapper JACKSON = new ObjectMapper();

  private final List<Map<String, ?>> _opList;
}
