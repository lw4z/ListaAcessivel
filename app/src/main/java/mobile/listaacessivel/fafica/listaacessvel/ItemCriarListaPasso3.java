package mobile.listaacessivel.fafica.listaacessvel;

/**
 * Created by ivan on 03/04/15.
 */
public class ItemCriarListaPasso3 {

    private String nome_produto;
    private String marca;
    private Double valor_produto;
    private int quantidade;

    public ItemCriarListaPasso3(String nome_produto, String marca, Double valor_produto, int quantidade) {
        super();
        this.nome_produto = nome_produto;
        this.marca = marca;
        this.valor_produto = valor_produto;
        this.quantidade = quantidade;
    }

    public ItemCriarListaPasso3(String nome_produto, String marca, Double valor_produto) {
        super();
        this.nome_produto = nome_produto;
        this.marca = marca;
        this.valor_produto = valor_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor_produto() {
        return valor_produto;
    }

    public void setValor_produto(Double valor_produto) {
        this.valor_produto = valor_produto;
    }
}
