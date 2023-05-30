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
      //登録の上限値
      int poke_limit = 1281;
      // コマンドを格納(poke get なら get)
      String command = args[0];
      // オプション格納(poke get 10なら 10)
      String option = null;

      // オプションが存在する時だけ変数に入れる
      if (args.length == 2) {
        option = args[1];
      } else if(args.length == 1){
        System.out.println("値を入力して下さい．");
      } else if(args.length >= 3){
        System.out.println("間にスペースを入れないでください．");
      }

      // コマンドごとに処理を分岐
      if (option != null && command.equals("get")) {
        int limit = Integer.parseInt(option);
        //エラー処理
        if(limit > poke_limit){
            System.out.println("その数まで登録されていません．" + limit + "までの値を入力して下さい");
        }else if(limit <= 0){
            System.out.println("0以下の値は入力できません．");
        }else{
             new GetPokeNameList(limit).run();

        }
      }
      
      if (option != null && command.equals("status")) {
        String name = option;
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
