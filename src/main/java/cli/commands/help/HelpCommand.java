package cli.commands.hello;

import cli.utils.Logger;

public class HelloCommand implements Runnable {
  @Override
  public void run() {
    Logger.success("poke get 数字:ポケモンの名前を数字分リスト表示します");
    System.out.println();
    Logger.success("poke status ポケモン名(英名):指定されたポケモンの種族値を表示します");
    System.out.println();
  }
}