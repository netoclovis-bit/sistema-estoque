# Sistema de Estoque de Produtos

Exercício da Primeira Avaliação: Paradigmas de Linguagens de Programação (UNIPÊ).

Conceitos aplicados: classes abstratas, herança, interfaces, polimorfismo (dinâmico e estático), composição e exceções personalizadas.

## Estrutura

| Classe | Papel |
|---|---|
| `EstoqueException` | Exceção base |
| `QuantidadeInvalidaException` | Preço/quantidade negativos |
| `ProdutoIndisponivelException` | Venda acima do estoque |
| `Vendavel` | Interface com `vender()` |
| `Product` | Classe abstrata (implementa `Vendavel`, sobrecarga de `aplicarDesconto`) |
| `ProdutoComum` | `preco × quantidade` |
| `ProdutoPerecivel` | 20% de desconto se `diasParaVencer <= 3` |
| `Estoque` | Composição: TEM UMA lista de `Product` |
| `EstoqueApp` | `main` com os testes |

## Como executar

```bash
cd src
javac *.java
java EstoqueApp
```
