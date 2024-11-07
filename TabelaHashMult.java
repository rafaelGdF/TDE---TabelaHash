public class TabelaHashMult extends TabelaHash{
    private static final double A = 0.6180339887;

    public TabelaHashMult(int tamanho) {
        super(tamanho);
    }

    @Override
    public int hash(String chave) {
        return (int) (tamanho * ((Math.abs(chave.hashCode() * A) % 1)));
    }
}