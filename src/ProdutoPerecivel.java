public class ProdutoPerecivel extends Product {
    private int diasParaVencer;

    public ProdutoPerecivel(String nome, double preco, int quantidade, int diasParaVencer)
            throws QuantidadeInvalidaException {
        super(nome, preco, quantidade);
        this.diasParaVencer = diasParaVencer;
    }

    @Override
    public double calcularValorTotal() {
        double total = getPreco() * getQuantidade();
        if (diasParaVencer <= 3) {
            total *= 0.8; // 20% de desconto automático
        }
        return total;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " | Vence em: " + diasParaVencer + " dia(s)";
    }

    public int getDiasParaVencer() { return diasParaVencer; }
}
