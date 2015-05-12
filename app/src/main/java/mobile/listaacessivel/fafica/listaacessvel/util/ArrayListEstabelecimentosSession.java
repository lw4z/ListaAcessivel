package mobile.listaacessivel.fafica.listaacessvel.util;

import java.util.ArrayList;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Estabelecimento;

/**
 * Created by ivan on 12/05/15.
 */
public final class ArrayListEstabelecimentosSession {

    static ArrayList<Estabelecimento> lista;

    public ArrayListEstabelecimentosSession(){

    }

    public ArrayListEstabelecimentosSession(ArrayList<Estabelecimento> lista){
        this.lista = lista;
    }

    public ArrayList<Estabelecimento> getListaEstabelecimentos(){
        return lista;
    }

}
