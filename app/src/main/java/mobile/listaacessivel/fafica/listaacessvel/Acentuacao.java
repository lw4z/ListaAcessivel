package mobile.listaacessivel.fafica.listaacessvel;

import java.text.Normalizer;

/**
 * Created by ivan on 08/04/15.
 */
public class Acentuacao {
    public static String limparAcentuacao(String palavra)     {
        //palavra = palavra.replaceAll(" ","_");
        palavra = Normalizer.normalize(palavra, Normalizer.Form.NFD);
        palavra = palavra.replaceAll("[^\\p{ASCII}]", "");
        palavra = palavra.toLowerCase();
        return palavra;
    }
}
