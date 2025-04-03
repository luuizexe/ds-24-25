package e2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EuroCoinCollectionTest {
    private static EuroCoin e1, e2, e3, e4, e5, e6, e7;
    private static EuroCoinCollection ec;

    @BeforeAll
    static void setUp() {
        e1 = new EuroCoin(Valor.EUR02, Pais.ES, "Felipe VI", 2020);
        e2 = new EuroCoin(Valor.EUR02, Pais.ES, "Felipe VI", 2020);//Repetida de 1
        e3 = new EuroCoin(Valor.EUR02, Pais.ES, "Felipe VI", 2080);//Repetida de 1 pero con otro anho
        e4 = new EuroCoin(Valor.CENT10, Pais.IT, "Torre Pisa", 2018);
        e5 = new EuroCoin(Valor.CENT10, Pais.IT, "Coliseo de Roma", 2018);//Repetida de 4 con otro diseno
        e6 = new EuroCoin(Valor.CENT05, Pais.CY, "Rey Maldivas", 2017);
        e7 = new EuroCoin(Valor.CENT10, Pais.CY, "Rey Maldivas", 2017);//Repetida de 6 con otro valor
    }

    @BeforeEach
    void resetCollection() {
        ec = new EuroCoinCollection();
    }

    @Test
    void testComparable() {
        ec.addCoin(e1);
        ec.addCoin(e4);
        ec.addCoin(e5);
        ec.addCoin(e6);
        ec.addCoin(e7);

        List<EuroCoin> sortedCoins = new ArrayList<>(ec.getEuroCoins());

        Collections.sort(sortedCoins);

        assertEquals(e1, sortedCoins.getFirst());
        assertEquals(e7, sortedCoins.get(1));
        assertEquals(e5, sortedCoins.get(2));
        assertEquals(e4, sortedCoins.get(3));
        assertEquals(e6, sortedCoins.get(4));
    }

    @Test
    void testComparator() {
        ec.addCoin(e1);
        ec.addCoin(e4);
        ec.addCoin(e5);
        ec.addCoin(e6);
        ec.addCoin(e7);

        List<EuroCoin> sortedCoins = new ArrayList<>(ec.getEuroCoins());

        Collections.sort(sortedCoins, new EuroCoinComparator());

        assertEquals(e7, sortedCoins.get(0)); // País CY, Valor 10, Año 2017
        assertEquals(e6, sortedCoins.get(1)); // País CY, Valor 5, Año 2017
        assertEquals(e4, sortedCoins.get(2)); // País IT, Valor 10, Año 2018
        assertEquals(e5, sortedCoins.get(3)); // País IT, Valor 10, Año 2018
        assertEquals(e1, sortedCoins.get(4)); // País ES, Valor 200, Año 2020
    }

    @Test
    void testAddCoin() {
        assertTrue(ec.addCoin(e1));
        assertEquals(1, ec.countCoins());

        assertFalse(ec.addCoin(e2));//Moneda repetida
        assertEquals(1, ec.countCoins());

        assertFalse(ec.addCoin(e3));//Moneda repetida
        assertEquals(1, ec.countCoins());

        assertTrue(ec.addCoin(e4));
        assertEquals(2, ec.countCoins());

        assertTrue(ec.addCoin(e6));
        assertEquals(3, ec.countCoins());

        assertTrue(ec.addCoin(e7));
        assertEquals(4, ec.countCoins());

        assertThrows(IllegalArgumentException.class, () -> ec.addCoin(null));//Moneda invalida
        assertEquals(4, ec.countCoins());
    }

    @Test
    void testRemoveCoin() {
        ec.addCoin(e1);
        ec.addCoin(e4);
        ec.addCoin(e5);
        ec.addCoin(e6);

        assertTrue(ec.removeCoin(e1));
        assertEquals(3, ec.countCoins());

        assertFalse(ec.removeCoin(e2));//Moneda que no esta en la lista
        assertEquals(3, ec.countCoins());

        assertFalse(ec.removeCoin(e3));//Moneda que no esta en la lista
        assertEquals(3, ec.countCoins());

        assertTrue(ec.removeCoin(e4));
        assertEquals(2, ec.countCoins());

        assertTrue(ec.removeCoin(e5));
        assertEquals(1, ec.countCoins());

        assertTrue(ec.removeCoin(e6));
        assertEquals(0, ec.countCoins());

        assertThrows(IllegalArgumentException.class, () -> ec.removeCoin(null));//Moneda invalida
        assertEquals(0, ec.countCoins());
    }

    @Test
    void testTotalValue() {
        ec.addCoin(e1);
        ec.addCoin(e4);
        ec.addCoin(e6);
        ec.addCoin(e7);

        assertEquals(225, ec.totalValue());

        assertTrue(ec.removeCoin(e1));
        assertEquals(25, ec.totalValue());

        assertFalse(ec.removeCoin(e2));//Moneda que no esta en la lista
        assertEquals(25, ec.totalValue());

        assertTrue(ec.removeCoin(e4));
        assertEquals(15, ec.totalValue());

        assertTrue(ec.removeCoin(e6));
        assertEquals(10, ec.totalValue());

        assertTrue(ec.removeCoin(e7));
        assertEquals(0, ec.countCoins());
    }

    @Test
    void testContains() {
        ec.addCoin(e1);
        ec.addCoin(e4);
        ec.addCoin(e6);
        assertTrue(ec.contains(e1));
        assertTrue(ec.contains(e2));//Moneda que es igual a e1
        assertTrue(ec.contains(e3));//Moneda que es igual a e1
        assertFalse(ec.contains(e7));//Moneda que no esta en la lista
        assertThrows(IllegalArgumentException.class, () -> ec.contains(null));//Moneda invalida
    }
}