package cli.commands.get;

import cli.utils.HttpRequest;
import cli.utils.Logger;
import cli.commands.status.GetPokeStatus;

public class GetPokeNameList implements Runnable {
  private Number limit;

  public GetPokeNameList(Number limit) {
    this.limit = limit;
  }

  public void run() {
    // ポケモンのデータを取得
    // 参照: https://pokeapi.co/docs/v2#pokemon
    HttpRequest fetcher = new HttpRequest("https://pokeapi.co/api/v2/pokemon?limit=" + limit);
    String res = fetcher.getResponse();
 
    // resからポケモンの名前を取り出す
    String[] pokemonNames = res.split("\"name\":\"");

    // ポケモンの名前とステータスを表示する
    for (int i = 1; i < pokemonNames.length; i++) {
      String pokemonName = pokemonNames[i].split("\"")[0];
      Logger.success(pokemonName);

      // ポケモンのステータスを取得して表示する
      GetPokeStatus pokeStatus = new GetPokeStatus(pokemonName);
      pokeStatus.run();
    }
  }
}
