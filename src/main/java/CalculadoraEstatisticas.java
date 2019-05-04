import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.PluginCotuba;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;
import java.util.List;
import java.util.Map;

public class CalculadoraEstatisticas implements PluginCotuba {

    @Override
    public String css() {
        return null;
    }

    @Override
    public void gerou(Ebook ebook) {
        List<Capitulo> capitulos = ebook.getCapitulos();

        ContagemPalavra palavraQuantidade = new ContagemPalavra();
        for (Capitulo capitulo : capitulos) {
            String html = capitulo.getConteudoHtml();
            Document document = Jsoup.parse(html);
            String texto = document.body().text();
            String textoSemPontuacao = texto.replaceAll("\\p{Punct}", "");
            String textoDeComposto = Normalizer.normalize(textoSemPontuacao, Normalizer.Form.NFD);

            texto = textoDeComposto.replaceAll("[^\\p{ASCII}]", "");
            String[] palavras = texto.split("\\s+");
            for(String palavra : palavras) {
                palavraQuantidade.adicionaPalavra(palavra);
            }

            for(Map.Entry<String, Integer> palavraQtd : palavraQuantidade.entrySet()) {
                System.out.println(palavraQtd.getKey() + " - " + palavraQtd.getValue());
            }
        }
        
    }
}
