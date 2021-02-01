package luj.game.robot.admin.internal.web.cluster;

import static java.util.stream.Collectors.toList;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import luj.game.robot.admin.internal.web.cluster.row.RowOpRenderer;
import luj.game.robot.admin.internal.web.cluster.row.ValueMapMaker;
import luj.game.robot.admin.internal.web.cluster.row.ValueMapRenderer;

public class BodyRowWithOpRenderer {

  public BodyRowWithOpRenderer(List<List<?>> rowList, List<String> header,
      Map<String, Function<Object, ?>> renderer, List<List<?>> jumpList) {
    _rowList = rowList;
    _header = header;
    _renderer = renderer;
    _jumpList = jumpList;
  }

  public List<Map<String, ?>> render() {
    return _rowList.stream()
        .map(r -> renderRow(r, _header))
        .collect(toList());
  }

  @SuppressWarnings("unchecked")
  private Map<String, Collection<?>> renderRow(List<?> row, List<String> header) {
    int headerCount = header.size();
    List<?> dataList = row.subList(0, headerCount);
    Map<String, Object> dataMap = new ValueMapMaker(dataList, header).make();

    List<?> opList = row.subList(headerCount, row.size());
    return ImmutableMap.of(
        "data", new ValueMapRenderer(dataMap, _renderer).render().values(),
        "jump", _jumpList.stream().map(j -> renderJump(j, dataMap)).collect(toList()),
        "op", new RowOpRenderer((List<Map<String, ?>>) opList).render());
  }

  @SuppressWarnings("unchecked")
  private Map<String, String> renderJump(List<?> jump, Map<String, Object> dataMap) {
    return ImmutableMap.of(
        "name", (String) jump.get(0),
        "to", ((Function<Map<String, Object>, String>) jump.get(1)).apply(dataMap)
    );
  }

  private final List<List<?>> _rowList;

  private final List<String> _header;
  private final Map<String, Function<Object, ?>> _renderer;

  private final List<List<?>> _jumpList;
}
