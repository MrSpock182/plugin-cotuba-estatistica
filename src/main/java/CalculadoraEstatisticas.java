import cotuba.plugin.ICapitulo;
import cotuba.plugin.IEbook;
import cotuba.plugin.PluginAcaoAposGeracao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;
import java.util.Map;

public class CalculadoraEstatisticas implements PluginAcaoAposGeracao {

    @Override
    public void gerou(IEbook iEbook) {
        ContagemPalavra palavraQuantidade = new ContagemPalavra();

        for (ICapitulo capitulo : iEbook.getCapitulos()) {

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
