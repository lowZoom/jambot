package luj.game.robot.internal.concurrent.instance.command;

import java.util.Random;
import luj.game.robot.api.action.RobotCommand;

final class CmdRandomImpl implements RobotCommand.Random {

  @Override
  public int randInt(int minInclude, int maxExclude) {
    return minInclude + RAND.nextInt(maxExclude - minInclude);
  }

  private static final Random RAND = new Random();
}
