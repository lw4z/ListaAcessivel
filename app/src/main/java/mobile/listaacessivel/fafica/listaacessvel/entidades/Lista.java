package mobile.listaacessivel.fafica.listaacessvel.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ivan on 01/04/15.
 */
public class Lista {

    private int id_lista;
    private String descricao;
    private String situacao;
    private String data_criacao;
    private String data_alteracao;
    private int quantidade_total = 0;
    private float valor_total = 0.0f;
    private Cliente cliente;
    private Estabelecimento estabelecimento;
    private List<Produto> produtos;

    //private SimpleDateFormat dmy = new SimpleDateFormat("d/M/y");

    public Lista() { }

    public Lista(String descricao, String situacao, Cliente cliente, Estabelecimento estabelecimento, List<Produto> produtos) {
        this.descricao = descricao;
        this.situacao = situacao;
        this.cliente = cliente;
        this.estabelecimento = estabelecimento;
        this.produtos = produtos;
        //this.data_criacao = dmy.format(new Date());
    }

    public Lista(int id_lista, String descricao, String situacao,
                 String data_criacao, Cliente cliente,
                 Estabelecimento estabelecimento, List<Produto> produtos) {
        this.id_lista = id_lista;
        this.descricao = descricao;
        this.situacao = situacao;
        this.data_criacao = data_criacao;
        this.cliente = cliente;
        this.estabelecimento = estabelecimento;
        this.produtos = produtos;
        //this.data_alteracao = dmy.format(new Date());

    }

    public Lista(int id_lista, String descricao, String situacao,
                 String data_criacao, String data_alteracao, int quantidade_total,
                 float valor_total, Cliente cliente,
                 Estabelecimento estabelecimento, List<Produto> produtos) {
        super();
        this.id_lista = id_lista;
        this.descricao = descricao;
        this.situacao = situacao;
        this.data_criacao = data_criacao;
        this.data_alteracao = data_alteracao;
        this.quantidade_total = quantidade_total;
        this.valor_total = valor_total;
        this.cliente = cliente;
        this.estabelecimento = estabelecimento;
        this.produtos = produtos;
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(String data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getData_alteracao() {
        return data_alteracao;
    }

    public void setData_alteracao(String data_alteracao) {
        this.data_alteracao = data_alteracao;
    }

    public int getQuantidade_total() {
        return quantidade_total;
    }

    public void setQuantidade_total(int quantidade_total) {
        this.quantidade_total = quantidade_total;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}