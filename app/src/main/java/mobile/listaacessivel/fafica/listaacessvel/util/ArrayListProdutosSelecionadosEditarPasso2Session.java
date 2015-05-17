package mobile.listaacessivel.fafica.listaacessvel.util;

import java.util.ArrayList;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;

/**
 * Created by ivan on 16/05/15.
 */
public class ArrayListProdutosSelecionadosEditarPasso2Session {

    static ArrayList<Produto> lista;

    public ArrayListProdutosSelecionadosEditarPasso2Session(){

    }

    public ArrayListProdutosSelecionadosEditarPasso2Session(ArrayList<Produto> lista){
        this.lista = lista;
    }

    public ArrayList<Produto> getListaProdutos(){
        return lista;
    }
}
