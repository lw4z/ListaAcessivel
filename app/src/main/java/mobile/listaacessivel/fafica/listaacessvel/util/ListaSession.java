package mobile.listaacessivel.fafica.listaacessvel.util;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Lista;

/**
 * Created by ivan on 12/05/15.
 */
public final class ListaSession {

    static Lista lista;

    public ListaSession(){

    }

    public ListaSession(Lista lista){
        this.lista = lista;
    }

    public Lista getLista(){
        return lista;
    }
}
