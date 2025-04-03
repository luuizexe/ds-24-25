package e3;

import java.util.Objects;

public record EuroCoin(Valor valor, Pais pais, String diseno, int ano) {

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

    @Override
    public String toString() {
        return valor + "-" + pais;
    }
}