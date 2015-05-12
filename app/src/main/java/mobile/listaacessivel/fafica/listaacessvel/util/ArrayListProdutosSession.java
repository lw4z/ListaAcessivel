package mobile.listaacessivel.fafica.listaacessvel.util;

import java.util.ArrayList;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;

/**
 * Created by ivan on 12/05/15.
 */
public final class ArrayListProdutosSession {

    static ArrayList<Produto> lista;

    public ArrayListProdutosSession(){

    }

    public ArrayListProdutosSession(ArrayList<Produto> lista){
        this.lista = lista;
    }

    public ArrayList<Produto> getListaProdutos(){
        return lista;
    }
}
