package e4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EuroCoinTest {
    private static EuroCoin e1, e2, e3, e4, e5, e6;

    @BeforeAll
    static void setUp() {
        e1 = new EuroCoin(200, Color.PLATA, Pais.ES, "Felipe VI", 2020);
        e2 = new EuroCoin(200, Color.PLATA, Pais.ES, "Felipe VI", 2020);
        e3 = new EuroCoin(200, Color.PLATA, Pais.ES, "Felipe VI", 2080);
        e4 = new EuroCoin(10, Color.BRONCE, Pais.IT, "Torre Pisa", 2018);
        e5 = new EuroCoin(5, Color.ORO, Pais.CY, "Rey Maldivas", 2017);
        e6 = new EuroCoin(10, Color.ORO, Pais.CY, "Rey Maldivas", 2017);
    }

    @Test
    void testEquals() {
        assertEquals(e1, e2);
        assertEquals(e1, e3);
        assertNotEquals(e1, null);
        assertNotEquals(e4, e3);
        assertNotEquals(e5, e6);
        assertNotEquals(e1, "String object");
    }
    @Test
    void testHashCode() {
        assertEquals(e1.hashCode(), e2.hashCode());
        assertEquals(e1.hashCode(), e3.hashCode());
        assertNotEquals(e4.hashCode(), e3.hashCode());
        assertNotEquals(e5.hashCode(), e6.hashCode());
    }
}
