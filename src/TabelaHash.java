import java.util.LinkedList;

public abstract class TabelaHash {
    protected int tamanho;
    protected LinkedList<String>[] tabela;
    protected int colisoes;
    protected int[] colisoesPorIndice;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
        this.colisoes = 0;
        this.colisoesPorIndice = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    public int getColisoes() {
        return colisoes;
    }

    public void printColisoesPorIndice() {
        System.out.println("Colisões por índice:");
        for (int i = 0; i < colisoesPorIndice.length; i++) {
            if (colisoesPorIndice[i] > 0) {
                System.out.println("Índice " + i + ": " + colisoesPorIndice[i] + " colisões");
            }
        }
    }

    public abstract int hash(String chave);

    public void inserir(String chave) {
        int ind = hash(chave);
        LinkedList<String> bc = tabela[ind];

        if (!bc.contains(chave)) {
            if(!bc.isEmpty()) {
                colisoes++;
                colisoesPorIndice[ind]++;
            }
            bc.add(chave);
        }
    }

    public boolean buscar(String chave) {
        int ind = hash(chave);
        LinkedList<String> bc = tabela[ind];
        return bc.contains(chave);
    }

    public int[] getDistribChaves() {
        int[] distrib = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            distrib[i] = tabela[i].size();
        }
        return distrib;
    }

    public void printDistribStats() {
        int max = 0, min = Integer.MAX_VALUE, sum = 0;

        for (int i = 0; i < tamanho; i++) {
            int currentSize = tabela[i].size();
            sum += currentSize;
            if (currentSize > max) max = currentSize;
            if (currentSize < min) min = currentSize;
        }
        double avg = (double) sum / tamanho;

        System.out.println("Distribuição de chaves:");
        System.out.println("Máximo: " + max);
        System.out.println("Mínimo: " + min);
        System.out.println("Média: " + avg);
    }
}
