package luj.game.robot.admin.internal.web.cluster.row;


import static java.util.stream.Collectors.toMap;

import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ValueMapRenderer {

  public ValueMapRenderer(Map<String, ?> valueMap, Map<String, Function<Object, ?>> renderMap) {
    _valueMap = valueMap;
    _renderMap = renderMap;
  }

  public Map<String, String> render() {
    return _valueMap.entrySet().stream()
        .map(this::render)
        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (u, v) -> {
          throw new IllegalStateException(String.format("Duplicate key %s", u));
        }, LinkedHashMap::new));
  }

  private Map.Entry<String, String> render(Map.Entry<String, ?> entry) {
    String key = entry.getKey();
    return Maps.immutableEntry(key, renderValue(key, entry.getValue()));
  }

  private String renderValue(String key, Object value) {
    Function<Object, ?> renderer = _renderMap.get(key);

    if (renderer == null) {
      return renderDefault(value);
    }
    return (String) renderer.apply(value);
  }

  private String renderDefault(Object value) {
    if (value instanceof Double) {
      return String.format("%.2f", value);
    }
    if (value instanceof List) {
      return renderList((List<?>) value);
    }
    return String.valueOf(value);
  }

  private String renderList(List<?> list) {
    if (list.size() == 2) {
      return "(" + list.get(0) + "," + list.get(1) + ")";
    }
    return list.toString();
  }

  private final Map<String, ?> _valueMap;

  private final Map<String, Function<Object, ?>> _renderMap;
}
