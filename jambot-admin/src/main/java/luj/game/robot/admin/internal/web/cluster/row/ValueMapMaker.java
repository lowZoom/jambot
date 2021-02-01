package luj.game.robot.admin.internal.web.cluster.row;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.ImmutableMap.toImmutableMap;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ValueMapMaker {

  public ValueMapMaker(List<?> row, List<String> header) {
    _row = row;
    _header = header;
  }

  public Map<String, Object> make() {
    int columnCount = _header.size();
    checkState(_row.size() == columnCount);

    return IntStream.range(0, columnCount)
        .mapToObj(i -> Maps.immutableEntry(_header.get(i), _row.get(i)))
        .collect(toImmutableMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  private final List<?> _row;

  private final List<String> _header;
}
