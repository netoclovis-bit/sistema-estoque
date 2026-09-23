public abstract class Product implements Vendavel {
    private String nome;
    private double preco;
    private int quantidade;

    public Product(String nome, double preco, int quantidade) throws QuantidadeInvalidaException {
        if (preco < 0) {
            throw new QuantidadeInvalidaException("Preço inválido para '" + nome + "': " + preco);
        }
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException("Quantidade inválida para '" + nome + "': " + quantidade);
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Método abstrato: cada subclasse decide como calcular
    public abstract double calcularValorTotal();

    public String getDescricao() {
        return String.format("%s | Preço: R$ %.2f | Quantidade: %d", nome, preco, quantidade);
    }

    @Override
    public void vender(int quantidadeDesejada) throws ProdutoIndisponivelException {
        if (quantidadeDesejada > quantidade) {
            throw new ProdutoIndisponivelException("Estoque insuficiente de '" + nome
                    + "': pedido " + quantidadeDesejada + ", disponível " + quantidade);
        }
        quantidade -= quantidadeDesejada;
    }

    // Sobrecarga (polimorfismo estático)
    public void aplicarDesconto(double percentual) {
        preco -= preco * percentual / 100;
    }

    public void aplicarDesconto(double percentual, double descontoMaximo) {
        double desconto = preco * percentual / 100;
        if (desconto > descontoMaximo) {
            desconto = descontoMaximo;
        }
        preco -= desconto;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }
}
