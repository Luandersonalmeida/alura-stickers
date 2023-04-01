import java.io.InputStream;
import java.net.URL;
import java.util.List;
public class App {
    public static void main(String[] args) throws Exception {

        // String  url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
       
        String  url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
           
        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        var gerador = new GeradorDeFigurinhas();
        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);
            
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
            
            gerador.cria(inputStream, nomeArquivo);
            System.out.println(conteudo.getTitulo()); 
            System.out.println();
        }
    }
}
