import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioTabela {
    private List<String> nomes;

    public RelatorioTabela(String filePath) throws IOException {
        nomes = loadNames(filePath);
    }

    private List<String> loadNames(String filePath) throws IOException {
        List<String> nomes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                nomes.add(line);
            }
        }
        return nomes;
    }

    public void Testar() {
        int tamTabela = 5000;
        TabelaHash tabelaHashMod = new TabelaHashMod(tamTabela);
        TabelaHash tabelaHashMult = new TabelaHashMult(tamTabela);

        long startTime = System.currentTimeMillis();
        for (String nome : nomes) {
            tabelaHashMod.inserir(nome);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Tabela Hash Mod:");
        System.out.println("Número de colisões: " + tabelaHashMod.getColisoes());
        System.out.println("Tempo de inserção: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        for (String nome : nomes) {
            tabelaHashMult.inserir(nome);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Tabela Hash Multiplicação:");
        System.out.println("Número de colisões: " + tabelaHashMult.getColisoes());
        System.out.println("Tempo de inserção: " + (endTime - startTime) + " ms");

        int[] distribMod = tabelaHashMod.getDistribChaves();
        int[] distribMult = tabelaHashMult.getDistribChaves();
        System.out.println("Distribuição das chaves - Tabela Mod:");
        printDistribution(distribMod);
        System.out.println("Distribuição das chaves - Tabela Multiplicação:");
        printDistribution(distribMult);

        System.out.println("-------------------------------");
        tabelaHashMod.printDistribStats();
        tabelaHashMult.printDistribStats();
    }

    private void printDistribution(int[] distrib) {
        for (int i = 0; i < distrib.length; i++) {
            if (distrib[i] > 0) {
                System.out.println("Índice " + i + ": " + distrib[i]);
            }
        }
    }
}