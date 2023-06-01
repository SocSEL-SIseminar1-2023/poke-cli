package cli;

import cli.commands.get.GetPokeNameList;
import cli.commands.status.GetPokeStatus;
import cli.commands.hello.HelloCommand;

public class CLI implements Runnable {
  private String[] args;

  CLI(String[] args) {
    this.args = args;
  }

  @Override
  public void run() {
    try {
      // コマンドを格納(poke get なら get)
      String command = args[0];
      // オプション格納(poke get 10なら 10)
      String option1 = null;
// オプション格納(poke get 10 20なら 20)
      String option2 = null;

      // オプションが存在する時だけ変数に入れる
      if (args.length >= 2) {
        option1 = args[1];
      }

      if (args.length >= 3) {
        option2 = args[2];
      }

      // コマンドごとに処理を分岐
      if (option1 != null && option2 == null && command.equals("get")) {
        int limit = Integer.parseInt(option1);
        new GetPokeNameList(limit).run();
      }
      //getコマンドで2つ目の数値があった場合の処理を追加
      if (option1 != null && option2 != null && command.equals("get")) {
        int start  = Integer.parseInt(option1);
        int limit = Integer.parseInt(option2);
        new GetPokeNameList(start, limit).run();
      }
      
      if (option1 != null && option2 == null && command.equals("status")) {
        String name = option1;
        new GetPokeStatus(name).run();
      }
      
      if (command.equals("hello")) {
        new HelloCommand().run();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
