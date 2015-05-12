package mobile.listaacessivel.fafica.listaacessvel.util;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Cliente;

/**
 * Created by ivan on 12/05/15.
 */
public final class ClienteSession {

    static Cliente cliente;

    public ClienteSession(){

    }

    public ClienteSession(Cliente cliente){
        this.cliente = cliente;
    }

    public Cliente getCliente(){
        return cliente;
    }
}
