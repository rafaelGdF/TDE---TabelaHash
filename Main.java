import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            RelatorioTabela teste = new RelatorioTabela("src/names_5000.csv");
            teste.Testar();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
