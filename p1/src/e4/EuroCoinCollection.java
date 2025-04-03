package e4;

import java.util.HashSet;
import java.util.Set;

public class EuroCoinCollection {
    // Declaramos el Set para almacenar euroCoins
    private Set<EuroCoin> euroCoins;

    // Constructor
    public EuroCoinCollection() {
        euroCoins = new HashSet<>();
    }

    // Metemos la euroCoin en la lista, devolviendo true si se ha insertado correctamente
    public boolean addCoin(EuroCoin coin) {
        if (coin == null) {
            throw new IllegalArgumentException("Coin cannot be null");
        }
        return euroCoins.add(coin);
    }

    // Eliminamos la euroCoin en la lista, devolviendo true si se ha eliminado correctamente
    public boolean removeCoin(EuroCoin coin) {
        if (coin == null) {
            throw new IllegalArgumentException("Coin cannot be null");
        }
        return euroCoins.remove(coin);
    }

    // Contamos el numero de monedas de la lista
    public int countCoins() {
        return euroCoins.size();
    }

    // Devolvemos el valor nominal de las monedas sumadas
    public int totalValue(){
        int total=0;
        for(EuroCoin coin:euroCoins){
            total+=coin.valor();
        }
        return total;
    }

    // Verificamos si una moneda esta en la lista
    public boolean contains(EuroCoin coin){
        if (coin == null) {
            throw new IllegalArgumentException("Coin cannot be null");
        }
        return euroCoins.contains(coin);
    }
}
