package mobile.listaacessivel.fafica.listaacessvel.entidades;

import java.util.List;

/**
 * Created by ivan on 02/04/15.
 */
public class Estabelecimento{


    private int id_estabelecimento;
    private String nome_fantasia;
    private String nome_juridico;
    private String email;
    private String categoria;
    private String cnpj;
    private Endereco endereco;
    private List <String> telefones;


    public Estabelecimento(){

    }

    public Estabelecimento(String nome_fantasia, String nome_juridico,
                           String email, String categoria, String cnpj, Endereco endereco,
                           List<String> telefones) {
        super();
        this.nome_fantasia = nome_fantasia;
        this.nome_juridico = nome_juridico;
        this.email = email;
        this.categoria = categoria;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefones = telefones;

    }

    public Estabelecimento(int id_estabelecimento, String nome_fantasia,
                           String nome_juridico, String email, String categoria, String cnpj,
                           Endereco endereco, List<String> telefones) {
        super();
        this.id_estabelecimento = id_estabelecimento;
        this.nome_fantasia = nome_fantasia;
        this.nome_juridico = nome_juridico;
        this.email = email;
        this.categoria = categoria;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefones = telefones;
        //this.administrador = administrador;
    }


    public int getId_estabelecimento() {
        return id_estabelecimento;
    }


    public void setId_estabelecimento(int id_estabelecimento) {
        this.id_estabelecimento = id_estabelecimento;
    }


    public String getNome_fantasia() {
        return nome_fantasia;
    }


    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }


    public String getNome_juridico() {
        return nome_juridico;
    }


    public void setNome_juridico(String nome_juridico) {
        this.nome_juridico = nome_juridico;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategoria() {
        return categoria;
    }


    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public String getCnpj() {
        return cnpj;
    }


    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    public Endereco getEndereco() {
        return endereco;
    }


    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    public List<String> getTelefones() {
        return telefones;
    }


    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

}