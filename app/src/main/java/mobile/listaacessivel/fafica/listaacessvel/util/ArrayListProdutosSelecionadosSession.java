package mobile.listaacessivel.fafica.listaacessvel.util;

import java.util.ArrayList;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;

/**
 * Created by ivan on 14/05/15.
 */
public final class ArrayListProdutosSelecionadosSession {
    static ArrayList<Produto> lista;

    public ArrayListProdutosSelecionadosSession(){

    }

    public ArrayListProdutosSelecionadosSession(ArrayList<Produto> lista){
        this.lista = lista;
    }

    public ArrayList<Produto> getListaProdutos(){
        return lista;
    }
}
