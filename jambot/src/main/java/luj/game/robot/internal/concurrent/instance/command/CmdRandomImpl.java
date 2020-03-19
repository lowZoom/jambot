package luj.game.robot.internal.concurrent.instance.command;

import java.util.Random;
import luj.game.robot.api.action.RobotCommand;

final class CmdRandomImpl implements RobotCommand.Random {

  @Override
  public int randInt(int minInclude, int maxExclude) {
    return minInclude + RAND.nextInt(maxExclude - minInclude);
  }

  @Override
  public boolean randBool(double likelihood) {
    return RAND.nextDouble() < likelihood;
  }

  private static final Random RAND = new Random();
}
