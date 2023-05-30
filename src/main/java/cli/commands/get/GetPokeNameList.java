import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class PokemonNameConverter {
    private static final String JSON_FILE_PATH = "path/to/json/file.json";

    public static void main(String[] args) {
        // JSONファイルの読み込み
        Map<String, String> nameMappings = loadNameMappingsFromJson(JSON_FILE_PATH);

        // ポケモン名の変換
        String[] pokemonNames = {"Bulbasaur", "Ivysaur"};
        for (String name : pokemonNames) {
            String convertedName = nameMappings.getOrDefault(name, name);
            System.out.println("English: " + name + ", Japanese: " + convertedName);
        }
    }

    private static Map<String, String> loadNameMappingsFromJson(String filePath) {
        Map<String, String> nameMappings = new HashMap<>();

        try (FileReader reader = new FileReader(filePath)) {
            // JSONファイルの内容を文字列として読み込み
            StringBuilder jsonContent = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                jsonContent.append((char) character);
            }

            // JSON文字列をパースしてマッピングを取得
            JSONArray jsonArray = new JSONArray(new JSONTokener(jsonContent.toString()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String japaneseName = jsonObject.getString("ja");
                String englishName = jsonObject.getString("en");
                nameMappings.put(englishName, japaneseName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nameMappings;
    }
}

