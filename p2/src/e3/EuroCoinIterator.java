package e3;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EuroCoinIterator implements Iterator<EuroCoin> {
    private final EuroCoinCollection euroCoinCollection;
    private final Iterator<EuroCoin> iterator;
    private final Pais pais;
    private EuroCoin currentCoin, nextCoin;
    private int expectedModCont;

    public EuroCoinIterator(EuroCoinCollection euroCoinCollection, Pais pais) {
        this.euroCoinCollection = euroCoinCollection;
        this.iterator = euroCoinCollection.getEuroCoins().iterator();
        this.pais = pais;
        this.expectedModCont = euroCoinCollection.getModCont();
        getNext();
    }

    private void getNext() {
        nextCoin = null;
        while (iterator.hasNext() && nextCoin == null) {
            EuroCoin coin = iterator.next();
            if (pais == null || pais.equals(coin.pais())) {
                nextCoin = coin;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return nextCoin != null;
    }

    @Override
    public EuroCoin next() {
        if(expectedModCont != euroCoinCollection.getModCont()) {
            throw new ConcurrentModificationException();
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        currentCoin = nextCoin;
        getNext();
        return currentCoin;
    }

    @Override
    public void remove() {
        if (expectedModCont != euroCoinCollection.getModCont()) {
            throw new ConcurrentModificationException();
        }
        if (currentCoin == null) {
            throw new IllegalStateException();
        }

        boolean removed = euroCoinCollection.removeCoin(currentCoin);
        if (removed) {
            expectedModCont++;
        }
        currentCoin = null;
    }

}
