package mobile.listaacessivel.fafica.listaacessvel.entidades;

/**
 * Created by ivan on 30/04/15.
 */
public abstract class Usuario {

    private int id_usuario;
    private String nome;
    private String email;
    private String senha;

    public Usuario(){

    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int id_usuario, String nome, String email, String senha) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId_usuario() {
        return id_usuario;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}

