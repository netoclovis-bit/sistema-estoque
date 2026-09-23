public class EstoqueApp {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        // 1. Cadastro de produtos
        try {
            estoque.adicionarProduto(new ProdutoComum("Caneta", 2.50, 100));
            estoque.adicionarProduto(new ProdutoComum("Caderno", 15.00, 30));
            estoque.adicionarProduto(new ProdutoPerecivel("Leite", 5.00, 50, 2));   // vence em <= 3 dias
            estoque.adicionarProduto(new ProdutoPerecivel("Queijo", 30.00, 10, 15));
            System.out.println("Produtos cadastrados com sucesso!");
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Erro de cadastro: " + e.getMessage());
        }

        // 2. Tentativa de cadastro com quantidade negativa
        System.out.println("\n--- Cadastro inválido ---");
        try {
            estoque.adicionarProduto(new ProdutoComum("Borracha", 1.00, -5));
        } catch (QuantidadeInvalidaException e) {
            System.out.println("QuantidadeInvalidaException capturada: " + e.getMessage());
        }

        System.out.println("\n--- Produtos no estoque ---");
        estoque.listarProdutos();

        // 3. Venda válida e venda acima do disponível
        System.out.println("\n--- Vendas ---");
        try {
            realizarVendas(estoque);
        } catch (QuantidadeInvalidaException e) {
            System.out.println("QuantidadeInvalidaException capturada: " + e.getMessage());
        } catch (ProdutoIndisponivelException e) {
            System.out.println("ProdutoIndisponivelException capturada: " + e.getMessage());
        } catch (EstoqueException e) { // a mais genérica vem por último
            System.out.println("Erro de estoque: " + e.getMessage());
        }

        // 4. Sobrecarga de aplicarDesconto (demonstração)
        System.out.println("\n--- Descontos (sobrecarga) ---");
        try {
            Product teste1 = new ProdutoComum("Mochila", 100.00, 1);
            teste1.aplicarDesconto(10);
            System.out.printf("Mochila com 10%%: R$ %.2f%n", teste1.getPreco());

            Product teste2 = new ProdutoComum("Tênis", 300.00, 1);
            teste2.aplicarDesconto(50, 40);
            System.out.printf("Tênis com 50%% (máx R$ 40): R$ %.2f%n", teste2.getPreco());
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // 5. Valor total do estoque
        System.out.println("\n--- Estoque após vendas ---");
        estoque.listarProdutos();
        System.out.printf("%nValor total do estoque: R$ %.2f%n", estoque.calcularValorTotalEstoque());
    }

    // Venda válida seguida de uma venda acima do disponível
    private static void realizarVendas(Estoque estoque) throws EstoqueException {
        estoque.venderProduto(0, 20);
        System.out.println("Venda bem-sucedida: 20 unidades de Caneta.");
        estoque.venderProduto(0, 500); // Caneta só tem 80 -> lança ProdutoIndisponivelException
        System.out.println("Esta linha não deve aparecer.");
    }
}
