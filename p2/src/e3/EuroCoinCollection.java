package e3;

import java.util.*;

public class EuroCoinCollection implements Iterable<EuroCoin> {
    // Declaramos la lista para almacenar euroCoins
    private List<EuroCoin> euroCoins;
    private int modCont;

    // Constructor
    public EuroCoinCollection() {
        euroCoins = new ArrayList<>();
        modCont = 0;
    }

    public List<EuroCoin> getEuroCoins() {
        return euroCoins;
    }

    // Metemos la euroCoin en la lista, devolviendo true si se ha insertado correctamente
    public boolean addCoin(EuroCoin coin) {
        if (coin == null) {
            throw new IllegalArgumentException("Coin cannot be null");
        }
        if (!euroCoins.contains(coin)) {
            euroCoins.add(coin);
            modCont++;
            return true;
        }
        return false;
    }

    // Eliminamos la euroCoin en la lista, devolviendo true si se ha eliminado correctamente
    public boolean removeCoin(EuroCoin coin) {
        if (coin == null) {
            throw new IllegalArgumentException("Coin cannot be null");
        }

        if (euroCoins.remove(coin)) {
            modCont++;
            return true;
        }
        return false;
    }

    // Contamos el numero de monedas de la lista
    public int countCoins() {
        return euroCoins.size();
    }

    // Devolvemos el valor nominal de las monedas sumadas
    public int totalValue() {
        int total = 0;
        for (EuroCoin coin : euroCoins) {
            total += coin.valor().getValorEnCents();
        }
        return total;
    }

    // Verificamos si una moneda esta en la lista
    public boolean contains(EuroCoin coin) {
        if (coin == null) {
            throw new IllegalArgumentException("Coin cannot be null");
        }
        return euroCoins.contains(coin);
    }

    public int getModCont() {
        return modCont;
    }

    public void incrementModCont() {
        modCont++;
    }

    public Iterator<EuroCoin> iterator(Pais pais) {
        return new EuroCoinIterator(this, pais);
    }

    // Implementación del iterador general
    @Override
    public Iterator<EuroCoin> iterator() {
        return euroCoins.iterator();
    }
}
