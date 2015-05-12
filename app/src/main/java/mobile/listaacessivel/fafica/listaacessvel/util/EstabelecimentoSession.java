package mobile.listaacessivel.fafica.listaacessvel.util;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Estabelecimento;

/**
 * Created by ivan on 12/05/15.
 */
public final class EstabelecimentoSession {

    static Estabelecimento estabelecimento;

    public EstabelecimentoSession(){

    }

    public EstabelecimentoSession(Estabelecimento estabelecimento){
        this.estabelecimento = estabelecimento;
    }

    public Estabelecimento getEstabelecimento(){
        return estabelecimento;
    }
}
