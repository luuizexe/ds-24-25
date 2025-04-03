package e4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EuroCoinCollectionTest {
    private static EuroCoin e1, e2, e3, e4, e5, e6;
    private static EuroCoinCollection ec;

    @BeforeAll
    static void setUp() {
        e1 = new EuroCoin(200, Color.PLATA, Pais.ES, "Felipe VI", 2020);
        e2 = new EuroCoin(200, Color.PLATA, Pais.ES, "Felipe VI", 2020);//Repetida de 1
        e3 = new EuroCoin(200, Color.PLATA, Pais.ES, "Felipe VI", 2080);//Repetida de 1
        e4 = new EuroCoin(10, Color.BRONCE, Pais.IT, "Torre Pisa", 2018);
        e5 = new EuroCoin(5, Color.ORO, Pais.CY, "Rey Maldivas", 2017);
        e6 = new EuroCoin(10, Color.ORO, Pais.CY, "Rey Maldivas", 2017);
    }

    @BeforeEach
    void resetCollection() {
        ec = new EuroCoinCollection();
    }

    @Test
    void testAddCoin() {
        assertTrue(ec.addCoin(e1));
        assertEquals(1, ec.countCoins());

        assertFalse(ec.addCoin(e2));
        assertEquals(1, ec.countCoins());

        assertFalse(ec.addCoin(e3));
        assertEquals(1, ec.countCoins());

        assertTrue(ec.addCoin(e4));
        assertEquals(2, ec.countCoins());

        assertTrue(ec.addCoin(e5));
        assertEquals(3, ec.countCoins());

        assertTrue(ec.addCoin(e6));
        assertEquals(4, ec.countCoins());

        assertThrows(IllegalArgumentException.class, () -> ec.addCoin(null));
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

        assertFalse(ec.removeCoin(e2));
        assertEquals(3, ec.countCoins());

        assertFalse(ec.removeCoin(e3));
        assertEquals(3, ec.countCoins());

        assertTrue(ec.removeCoin(e4));
        assertEquals(2, ec.countCoins());

        assertTrue(ec.removeCoin(e5));
        assertEquals(1, ec.countCoins());

        assertTrue(ec.removeCoin(e6));
        assertEquals(0, ec.countCoins());

        assertThrows(IllegalArgumentException.class, () -> ec.removeCoin(null));
        assertEquals(0, ec.countCoins());
    }

    @Test
    void testTotalValue() {
        ec.addCoin(e1);
        ec.addCoin(e4);
        ec.addCoin(e5);
        ec.addCoin(e6);

        assertEquals(225, ec.totalValue());

        assertTrue(ec.removeCoin(e1));
        assertEquals(25, ec.totalValue());

        assertFalse(ec.removeCoin(e2));
        assertEquals(25, ec.totalValue());

        assertTrue(ec.removeCoin(e4));
        assertEquals(15, ec.totalValue());

        assertTrue(ec.removeCoin(e5));
        assertEquals(10, ec.totalValue());

        assertTrue(ec.removeCoin(e6));
        assertEquals(0, ec.countCoins());
    }

    @Test
    void testContains() {
        ec.addCoin(e1);
        ec.addCoin(e4);
        ec.addCoin(e5);
        assertTrue(ec.contains(e1));
        assertTrue(ec.contains(e2));
        assertTrue(ec.contains(e3));
        assertFalse(ec.contains(e6));
        assertThrows(IllegalArgumentException.class, () -> ec.contains(null));
    }
}