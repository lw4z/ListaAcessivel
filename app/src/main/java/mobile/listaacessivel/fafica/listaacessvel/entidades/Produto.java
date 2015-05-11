package mobile.listaacessivel.fafica.listaacessvel.entidades;

/**
 * Created by ivan on 03/04/15.
 */
public class Produto {

    private int id_produto;
    private String descricao;
    private String categoria;
    private float peso = 0.0f;
    private int quantidade = 0;
    private float valor = 0.0f;
    private String validade;
    private String marca;
    private String codigo_barra;
    private String disponibilidade;
    private Estabelecimento estabelecimento;
    private boolean selecionado = false; //Atributo utilizado para o projeto Android

    public Produto() { }

    public Produto(String descricao, String categoria,
                   float peso, int quantidade, float valor,
                   String validade, String marca,
                   String codigo_barra, Estabelecimento estabelecimento) {
        super();
        this.descricao = descricao;
        this.categoria = categoria;
        this.peso = peso;
        this.quantidade = quantidade;
        this.valor = valor;
        this.validade = validade;
        this.marca = marca;
        this.codigo_barra = codigo_barra;
        this.estabelecimento = estabelecimento;
    }

    public Produto(int id_produto, String descricao, String categoria,
                   float peso, int quantidade, float valor,
                   String validade, String marca,
                   String codigo_barra, String disponibilidade,
                   Estabelecimento estabelecimento) {
        super();
        this.id_produto = id_produto;
        this.descricao = descricao;
        this.categoria = categoria;
        this.peso = peso;
        this.quantidade = quantidade;
        this.valor = valor;
        this.validade = validade;
        this.marca = marca;
        this.codigo_barra = codigo_barra;
        this.disponibilidade = disponibilidade;
        this.estabelecimento = estabelecimento;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }



}
