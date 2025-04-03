package e2;

import java.util.Comparator;

public class EuroCoinComparator implements Comparator<EuroCoin> {
    public int compare(EuroCoin e1, EuroCoin e2) {
        //Primero miramos el pais
        if (e1.pais().compareTo(e2.pais()) != 0) {
            return e1.pais().getNombre().compareTo(e2.pais().getNombre());
        } else {
            //Luego el valor
            if (e1.valor() != e2.valor()) {
                return Integer.compare(e2.valor().getValorEnCents(), e1.valor().getValorEnCents());
            } else {
                //Por ultimo el anho
                return Integer.compare(e1.ano(), e2.ano());
            }
        }
    }
}