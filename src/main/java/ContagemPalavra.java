import javax.swing.text.html.parser.Entity;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ContagemPalavra  {

    private TreeMap<String, Integer> palavrasQtd = new TreeMap<>();



    //DELEGATE METHOD
    public Set<Map.Entry<String, Integer>> entrySet() {
        return palavrasQtd.entrySet();
    }

    public void adicionaPalavra(String palavra) {
        if (palavrasQtd.containsKey(palavra)) {
            Integer qtd = palavrasQtd.get(palavra);
            palavrasQtd.put(palavra, ++qtd);
        } else {
            palavrasQtd.put(palavra, 1);
        }
    }

}
