package mobile.listaacessivel.fafica.listaacessvel.util;

import java.util.ArrayList;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;

/**
 * Created by ivan on 14/05/15.
 */
public class ArrayListProdutosEditarSession {
    static ArrayList<Produto> lista;

    public ArrayListProdutosEditarSession(){

    }

    public ArrayListProdutosEditarSession(ArrayList<Produto> lista){
        this.lista = lista;
    }

    public ArrayList<Produto> getListaProdutos(){
        return lista;
    }
}
