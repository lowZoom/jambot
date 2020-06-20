package luj.game.robot.internal.concurrent.instance.command.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import luj.game.robot.api.action.RobotCommand;
import okhttp3.OkHttpClient;
import okhttp3.Request;

final class NetworkHttpImpl implements RobotCommand.Http {

  @Override
  public String request(String url) {
    try {
      return OKHTTP.newCall(new Request.Builder().url(url).build()).execute().body().string();
    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  @Override
  public Map<String, Object> requestJsonMap(String url) {
    try {
      return JACKSON.readValue(request(url).trim(), Map.class);
    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private static final OkHttpClient OKHTTP = new OkHttpClient();

  private static final ObjectMapper JACKSON = new ObjectMapper();
}
