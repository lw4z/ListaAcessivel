package mobile.listaacessivel.fafica.listaacessvel.util;

import java.util.ArrayList;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Lista;

/**
 * Created by ivan on 12/05/15.
 */
public final class ArrayListListasSession {

    static ArrayList<Lista> lista;

    public ArrayListListasSession(){

    }

    public ArrayListListasSession(ArrayList<Lista> lista){
        this.lista = lista;
    }

    public ArrayList<Lista> getListaListas(){
        return lista;
    }
}
