import java.util.TreeMap;

public class ContagemPalavra extends TreeMap<String, Integer> {

    public void adicionaPalavra(String palavra) {
        if (containsKey(palavra)) {
            Integer qtd = get(palavra);
            super.put(palavra, ++qtd);
        } else {
            super.put(palavra, 1);
        }
    }

}
