package mobile.listaacessivel.fafica.listaacessvel.entidades;

/**
 * Created by ivan on 30/04/15.
 */
public class Endereco {

    private String rua;
    private String bairro;
    private String numero;
    private String complemento;
    private String referencia;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String rua, String bairro, String numero, String complemento, String referencia, String cidade, String estado, String cep){
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.referencia = referencia;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String toString(){
        String endereco = "Rua: "+rua+" Bairro: "+bairro+" N�: "+numero+" Complemento: "+complemento
                +"\nCidade: "+cidade+"-"+estado+" CEP: "+cep
                +"\nReferencia: "+referencia;

        return endereco;
    }
}

