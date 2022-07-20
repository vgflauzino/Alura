import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//Shift + Alt + O importa tudo que for necessário

public class JsonParser {
     
    //Expressão regular (regex): String que mostra um padrão de pesquisa e substituição
    //Pattern: expressão regular

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*"); {

    }
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\""); {
        
    }

    public List<Map<String, String>> parse(String json) {
    
    //Matcher: expressão regular 
        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("Não encontrou items.");

        }

        String[] items = matcher.group(1).split("*\\},\\}");
        
        List<Map<String, String>> dados = new ArrayList<>(); 

        for (String item : items) {

            Map<String, String> atributosItem = new HashMap<>();

            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while (matcherAtributosJson.find()) {
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                atributosItem.put(atributo, valor);

            }

            dados.add(atributosItem);

        }

        return dados;
        
    }
}
