package e3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import e3.Property.PropertyType;
import e3.Ad.AdType;

import static org.junit.jupiter.api.Assertions.*;


class AdTest {

    private static Property p1, p2, p3, p4;
    private static Ad a1, a2, a3, a4, a5;


    @BeforeAll
    static void setUp() {

        p1 = new Property(PropertyType.APARTMENT,
                "01234567890123456789",
                "Aurelio Aguirre Galarraga 100, 1-A, A Coruna",
                "15190",
                80,
                2,
                1
        );

        p2 = new Property(PropertyType.APARTMENT,
                "56789012345678901234",
                "Aurelio Aguirre Galarraga 202, 5-D, A Coruna",
                "15190",
                100,
                3,
                2
        );



        /* Same cadaster as h2 but the entered address and meters are different. */

        p3 = new Property(PropertyType.APARTMENT,
                "56789012345678901234",
                "Aurelio Aguirre Galarraga 202, 5º D (A Coruna)",
                "15190",
                95,
                3,
                2
        );

        p4 = new Property(PropertyType.LOCAL,
                "78901234567890123456",
                "Aurelio Aguirre Galarraga 100, A Coruna",
                "15190",
                0,
                0,
                0
        ); // Incomplete data.

        a1 = new Ad("RE/MAX",
                p1,
                AdType.PURCHASE,
                249000
        );

        a2 = new Ad("Engel & Volkers",
                p2,
                AdType.PURCHASE,
                300000
        );

        a3 = new Ad("RE/MAX",
                p3,
                AdType.RENTAL,
                350000
        ); // Wrong price for a rental.

        a4 = new Ad("Engel & Volkers",
                p4,
                AdType.RENTAL,
                5000
        );

        a5 = new Ad("Engel & Volkers",
                p2,
                AdType.PURCHASE,
                300000
        );
    }

    @Test
    void isPropertyEqual() {
        assertFalse(a1.isPropertyEqual(a2));
        assertFalse(a1.isPropertyEqual(a3));
        assertTrue(a2.isPropertyEqual(a3));
        assertFalse(a2.isPropertyEqual(a4));
    }

    @Test
    void isPriceNormal() {
        assertTrue(a1.isPriceNormal());
        assertTrue(a2.isPriceNormal());
        assertFalse(a3.isPriceNormal());
        assertTrue(a4.isPriceNormal());
    }

    @Test
    void priceMetersEuros() {
        assertEquals(3112.5, a1.priceMetersEuros(), 0.01);
        assertEquals(3000.0, a2.priceMetersEuros(), 0.01);

        assertThrows(ArithmeticException.class, () -> a4.priceMetersEuros());
    }

    @Test
    void dropPrice() {
        Ad tmp = new Ad(a1); // Copy constructor.
        tmp.dropPrice(5);
        assertEquals(236550, tmp.getPriceInEuros());

        assertThrows(IllegalArgumentException.class, () -> tmp.dropPrice(200));
    }

    @Test
    void dropPrice2() {
        Ad tmp = new Ad(a1);
        assertThrows(IllegalArgumentException.class, () -> tmp.dropPrice(-10));
    }

    @Test
    void testEquals() {
        assertNotEquals(a1, null);
        assertNotEquals(a1, p1);
        assertNotEquals(a1, a2);
        assertNotEquals(a1, a3);
        assertNotEquals(a2, a4);
        assertEquals(a2, a5);
        assertEquals(a2, a2);
    }

    @Test
    void testHashCode() {
        assertNotEquals(a1.hashCode(), p1.hashCode());
        assertNotEquals(a1.hashCode(), a2.hashCode());
        assertNotEquals(a1.hashCode(), a3.hashCode());
        assertNotEquals(a2.hashCode(), a4.hashCode());
        assertEquals(a2.hashCode(), a5.hashCode());
    }
}