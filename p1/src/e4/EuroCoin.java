package e4;

import java.util.Objects;

// Declaracion de Eurocoin
public record EuroCoin(int valor, Color color, Pais pais, String diseno, int ano) {

    // Dos monedas son iguales si tienen el mismo valor, pais y diseno
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EuroCoin euroCoin = (EuroCoin) o;
        return valor == euroCoin.valor && pais == euroCoin.pais && Objects.equals(diseno, euroCoin.diseno);
    }

    // Son iguales si el hashcode valor, pais y diseno coinciden
    @Override
    public int hashCode() {
        return Objects.hash(valor, pais, diseno);
    }
}