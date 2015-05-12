package mobile.listaacessivel.fafica.listaacessvel.util;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;

/**
 * Created by ivan on 12/05/15.
 */
public final class ProdutoSession {

    static Produto produto;

    public ProdutoSession(){

    }

    public ProdutoSession(Produto produto){
        this.produto = produto;
    }

    public Produto getProduto(){
        return produto;
    }
}
