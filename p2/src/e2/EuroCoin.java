package e2;

import java.util.Objects;

public record EuroCoin(Valor valor, Pais pais, String diseno, int ano) implements Comparable<EuroCoin> {

    // Dos monedas son iguales si tienen el mismo valor, país y diseño
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EuroCoin euroCoin = (EuroCoin) o;
        return valor.getValorEnCents() == euroCoin.valor.getValorEnCents() && pais == euroCoin.pais && Objects.equals(diseno, euroCoin.diseno);
    }

    // Son iguales si el hashcode de valor, país y diseño coinciden
    @Override
    public int hashCode() {
        return Objects.hash(valor.getValorEnCents(), pais, diseno);
    }

    // Ordena las monedas primero por año y luego por valor si los años son iguales
    @Override
    public int compareTo(EuroCoin o) {
        if (valor.getValorEnCents() != o.valor.getValorEnCents()) {
            return Integer.compare(o.valor.getValorEnCents(), valor.getValorEnCents());
        } else {
            if (pais.getNombre().compareTo(o.pais.getNombre()) != 0) {
                return pais.getNombre().compareTo(o.pais.getNombre());
            } else {
                return diseno.compareTo(o.diseno);
            }
        }
    }
}