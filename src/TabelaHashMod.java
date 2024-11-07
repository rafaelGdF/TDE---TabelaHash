public class TabelaHashMod extends TabelaHash{
    public TabelaHashMod(int tamanho) {
        super(tamanho);
    }

    @Override
    public int hash(String chave) {
        return Math.abs(chave.hashCode() % tamanho);
    }
}
