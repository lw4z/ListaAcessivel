package mobile.listaacessivel.fafica.listaacessvel;

/**
 * Created by ivan on 02/04/15.
 */
public class Estabelecimento {

    private String nome;
    private String bairro;
    private String cidade;

    public Estabelecimento(String nome, String bairro, String cidade) {
        super();
        this.nome = nome;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
