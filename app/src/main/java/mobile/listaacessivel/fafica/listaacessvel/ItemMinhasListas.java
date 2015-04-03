package mobile.listaacessivel.fafica.listaacessvel;

/**
 * Created by ivan on 01/04/15.
 */
public class ItemMinhasListas {
    private String titulo;
    private String descricao;

    public ItemMinhasListas(String titulo, String descricao) {
        super();
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
