package luj.game.robot.internal.concurrent.instance.command.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import luj.cluster.api.actor.Tellable;
import luj.game.robot.api.action.RobotCommand;
import luj.game.robot.internal.concurrent.instance.RobotInstanceDependency;
import luj.game.robot.internal.net.http.HttpHandleInvoker;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

final class NetworkHttpImpl implements RobotCommand.Http {

  NetworkHttpImpl(Tellable instanceRef, RobotInstanceDependency instanceDep) {
    _instanceRef = instanceRef;
    _instanceDep = instanceDep;
  }

  @Override
  public void request(String url, Class<? extends RobotCommand<?>> handler) {
    System.out.println("request: " + url);

    OKHTTP.newCall(new Request.Builder().url(url).build()).enqueue(new Callback() {
      @Override
      public void onResponse(Call call, Response response) throws IOException {
        String respStr = response.body().string();
        new HttpHandleInvoker(respStr, handler, _instanceRef, _instanceDep).invoke();
      }

      @Override
      public void onFailure(Call call, IOException e) {
        throw new UnsupportedOperationException(e);
      }
    });
  }

  @Override
  public String request(String url) {
    throw new UnsupportedOperationException();
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

  private final Tellable _instanceRef;
  private final RobotInstanceDependency _instanceDep;
}
