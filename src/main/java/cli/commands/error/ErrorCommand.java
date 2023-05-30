package cli.commands.error;

import cli.utils.Logger;

public class ErrorCommand implements Runnable {
  @Override
  public void run() {
    Logger.success("This is not a poke command. See 'poke help'.");
    System.out.println();
  }
}
