package luj.game.robot.internal.concurrent.instance.command;

import java.util.Collection;
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

  @Override
  public <T> T randElement(Collection<T> collection) {
    return collection.stream()
        .skip(randInt(0, collection.size()))
        .findFirst()
        .orElseThrow(UnsupportedOperationException::new);
  }

  private static final Random RAND = new Random();
}
