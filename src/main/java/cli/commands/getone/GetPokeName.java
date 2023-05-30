package cli.commands.getone;

import cli.utils.HttpRequest;
import cli.utils.Logger;

public class GetPokeName {
    private int p_num;

  public GetPokeName(int p_num) {
    this.p_num = p_num;
  }

  public void run() {
    // ポケモンのデータを取得
    // 参照: https://pokeapi.co/docs/v2#pokemon
    HttpRequest fetcher = new HttpRequest("https://pokeapi.co/api/v2/pokemon?limit=" + p_num);
    String res = fetcher.getResponse();
 
    // resからポケモンの名前を取り出す
    String[] pokemonNames = res.split("\"name\":\"");

    // ポケモンの名前を表示させる
    String pokemonName = pokemonNames[p_num].split("\"")[0];
    Logger.success(pokemonName); 
    System.out.println();
  }
}

