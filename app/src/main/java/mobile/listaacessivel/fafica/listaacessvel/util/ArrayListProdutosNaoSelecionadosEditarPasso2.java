package mobile.listaacessivel.fafica.listaacessvel.util;

import java.util.ArrayList;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;

/**
 * Created by ivan on 17/05/15.
 */
public class ArrayListProdutosNaoSelecionadosEditarPasso2 {
    static ArrayList<Produto> lista;

    public ArrayListProdutosNaoSelecionadosEditarPasso2(){

    }

    public ArrayListProdutosNaoSelecionadosEditarPasso2(ArrayList<Produto> lista){
        this.lista = lista;
    }

    public ArrayList<Produto> getListaProdutos(){
        return lista;
    }

}
