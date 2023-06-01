package cli.commands.get;

import cli.utils.HttpRequest;
import cli.utils.Logger;

public class GetPokeNameList implements Runnable {
    private int start;
    private Number limit;

  public GetPokeNameList(Number limit) {
    this.limit = limit;
    this.start = 1;
  }

  public GetPokeNameList(int start,Number limit) {
    this.start = start;
    this.limit = limit;
  }

  public void run() {
    // ポケモンのデータを取得
    // 参照: https://pokeapi.co/docs/v2#pokemon
    HttpRequest fetcher = new HttpRequest("https://pokeapi.co/api/v2/pokemon?limit=" + limit);
    String res = fetcher.getResponse();
 
    // resからポケモンの名前を取り出す
    String[] pokemonNames = res.split("\"name\":\"");
    
    // ポケモンの名前を表示させる
    for (int i = start; i < pokemonNames.length; i++) {
      String pokemonName = pokemonNames[i].split("\"")[0];
      Logger.success(pokemonName); 
      System.out.println();
    }
  }
}
