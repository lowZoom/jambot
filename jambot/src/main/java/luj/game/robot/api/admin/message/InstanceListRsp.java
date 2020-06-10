package luj.game.robot.api.admin.message;

import java.util.List;

public interface InstanceListRsp {

  interface Bot {

    int index();

    boolean connected();
  }

  List<Bot> instanceList();
}
