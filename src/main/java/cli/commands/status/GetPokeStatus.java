package cli.commands.status;

import cli.utils.HttpRequest;
import cli.utils.Logger;

public class GetPokeStatus implements Runnable {
  private String name;

  public GetPokeStatus(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    // ポケモンのデータを取得する
    // 参照: https://pokeapi.co/docs/v2#pokemon
    HttpRequest fetcher = new HttpRequest("https://pokeapi.co/api/v2/pokemon/" + name);
    String res = fetcher.getResponse();

    // resからheightを抜き出す
    String height = res.split("\"height\":")[1].split(",")[0];

    // resからweightを抜き出す
    String weight = res.split("\"weight\":")[1].split("}")[0];

    // resからtypesを抜き出す
    String[] types = res.split("\"types\":\\[")[1].split("\\]")[0].split("\\},\\{");

    // resからstatsを抜き出す
    String[] stats = res.split("\"stats\":\\[")[1].split("\\]")[0].split("\\},\\{");

    // resからabilitiesを抜き出す
    String[] abilities = res.split("\"abilities\":\\[")[1].split("\\]")[0].split("\\},\\{");
    
    Logger.attention("Pokemon status for " + name + ":");
    System.out.println();

    //高さを表示させる
    System.out.println("height: " + height);

    //重さを表示させる
    System.out.println("weight: " + weight);

    // タイプを表示させる
    for (String type: types) {
      String typeName = type.split("\"name\":\"")[1].split("\"")[0];
      Logger.log("type: " + typeName);
      System.out.println();
    }

    // 種族値を表示させる
    for (String stat: stats) {
      String statName = stat.split("\"name\":\"")[1].split("\"")[0];
      int baseStat = Integer.parseInt(stat.split("\"base_stat\":")[1].split(",")[0]);
      Logger.log(statName + ": ");
      Logger.success(Integer.toString(baseStat));
      System.out.println();
    }
    
    // 特性を表示させる
    for (String ability: abilities) {
        String abilityName = ability.split("\"name\":\"")[1].split("\"")[0];
        Logger.log("ability: " + abilityName);
        System.out.println();
    }

  }
}
