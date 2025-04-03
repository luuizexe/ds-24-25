package e1;

import e1.Aldeas.Galos.AldeaGalos;
import e1.Aldeas.Galos.TropaDruidas;
import e1.Aldeas.Galos.TropaFalanges;
import e1.Aldeas.Galos.TropaRayosDeTeutates;
import e1.Aldeas.Romanos.AldeaRomanos;
import e1.Aldeas.Romanos.TropaEquitesImperatoris;
import e1.Aldeas.Romanos.TropaLegionarios;
import e1.Aldeas.Romanos.TropaPretorianos;
import e1.Aldeas.Teutones.AldeaTeutones;
import e1.Aldeas.Teutones.TropaGuerrerosDeHacha;
import e1.Aldeas.Teutones.TropaGuerrerosDePorra;
import e1.Aldeas.Teutones.TropaPaladines;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TravianTest {
    static AldeaRomanos aldeaRomanos;
    static TropaLegionarios tropaLegionarios;
    static TropaPretorianos tropaPretorianosSinMejora;
    static TropaPretorianos tropaPretorianosConMejora;
    static TropaEquitesImperatoris tropaEquitesImperatoris;

    static AldeaGalos aldeaGalos;
    static TropaDruidas tropaDruidas;
    static TropaRayosDeTeutates tropaRayosDeTeutatesSinMejora;
    static TropaRayosDeTeutates tropaRayosDeTeutatesConMejora;
    static TropaFalanges tropaFalanges;

    static AldeaTeutones aldeaTeutones;
    static TropaGuerrerosDeHacha guerrerosDeHacha;
    static TropaGuerrerosDePorra tropaGuerrerosDePorraSinMejora;
    static TropaGuerrerosDePorra tropaGuerrerosDePorraConMejora;
    static TropaPaladines tropaPaladines;

    @BeforeAll
    static void setUp() {
        aldeaRomanos = new AldeaRomanos("AldeaA", 1, 30);
        tropaLegionarios = new TropaLegionarios(aldeaRomanos);
        tropaPretorianosConMejora = new TropaPretorianos(aldeaRomanos,true);
        tropaPretorianosSinMejora = new TropaPretorianos(aldeaRomanos,false);
        tropaEquitesImperatoris = new TropaEquitesImperatoris(aldeaRomanos);

        aldeaTeutones = new AldeaTeutones("AldeaB", 2, 20);
        guerrerosDeHacha = new TropaGuerrerosDeHacha(aldeaTeutones);
        tropaGuerrerosDePorraConMejora = new TropaGuerrerosDePorra(aldeaTeutones, true);
        tropaGuerrerosDePorraSinMejora = new TropaGuerrerosDePorra(aldeaTeutones, false);
        tropaPaladines = new TropaPaladines(aldeaTeutones);

        aldeaGalos = new AldeaGalos("AldeaC", 3, 30);
        tropaFalanges = new TropaFalanges(aldeaGalos);
        tropaRayosDeTeutatesConMejora = new TropaRayosDeTeutates(aldeaGalos,true);
        tropaRayosDeTeutatesSinMejora = new TropaRayosDeTeutates(aldeaGalos,false);
        tropaDruidas = new TropaDruidas(aldeaGalos);

        Travian.batalla(aldeaTeutones, aldeaGalos);
    }

    @BeforeEach
    void resetCollection() {
        aldeaRomanos = new AldeaRomanos("AldeaA", 1, 30);
        aldeaTeutones = new AldeaTeutones("AldeaB", 2, 20);
        aldeaGalos = new AldeaGalos("AldeaC", 3, 30);
    }

    @Test
    void testAddAndRemoveTropa() {
        assertTrue(aldeaRomanos.addTropa(tropaLegionarios));
        assertTrue(aldeaRomanos.addTropa(tropaLegionarios));//Insertamos tropa repetida
        assertTrue(aldeaRomanos.addTropa(tropaPretorianosConMejora));
        assertTrue(aldeaRomanos.addTropa(tropaPretorianosSinMejora));
        assertTrue(aldeaRomanos.addTropa(tropaEquitesImperatoris));
        assertFalse(aldeaRomanos.addTropa(tropaDruidas));//Tropa que no pertenece a la aldea
        assertFalse(aldeaRomanos.addTropa(null));

        assertTrue(aldeaTeutones.addTropa(guerrerosDeHacha));
        assertTrue(aldeaTeutones.addTropa(guerrerosDeHacha));//Insertamos tropa repetida
        assertTrue(aldeaTeutones.addTropa(tropaGuerrerosDePorraConMejora));
        assertTrue(aldeaTeutones.addTropa(tropaGuerrerosDePorraSinMejora));
        assertTrue(aldeaTeutones.addTropa(tropaPaladines));
        assertFalse(aldeaTeutones.addTropa(tropaLegionarios));//Tropa que no pertenece a la aldea
        assertFalse(aldeaTeutones.addTropa(null));

        assertTrue(aldeaGalos.addTropa(tropaDruidas));
        assertTrue(aldeaGalos.addTropa(tropaDruidas));//Insertamos tropa repetida
        assertTrue(aldeaGalos.addTropa(tropaRayosDeTeutatesConMejora));
        assertTrue(aldeaGalos.addTropa(tropaRayosDeTeutatesSinMejora));
        assertTrue(aldeaGalos.addTropa(tropaFalanges));
        assertFalse(aldeaGalos.addTropa(tropaLegionarios));//Tropa que no pertenece a la aldea
        assertFalse(aldeaGalos.addTropa(null));

        assertTrue(aldeaRomanos.removeTropa(tropaLegionarios));
        assertTrue(aldeaRomanos.removeTropa(tropaLegionarios));
        assertTrue(aldeaRomanos.removeTropa(tropaPretorianosConMejora));
        assertTrue(aldeaRomanos.removeTropa(tropaPretorianosSinMejora));
        assertTrue(aldeaRomanos.removeTropa(tropaEquitesImperatoris));
        assertFalse(aldeaRomanos.removeTropa(tropaEquitesImperatoris)); //Eliminamos tropa ya eliminada
        assertFalse(aldeaRomanos.removeTropa(tropaDruidas)); //Tropa que no pertenece a la aldea
        assertFalse(aldeaRomanos.removeTropa(null));

        assertTrue(aldeaTeutones.removeTropa(guerrerosDeHacha));
        assertTrue(aldeaTeutones.removeTropa(guerrerosDeHacha));
        assertTrue(aldeaTeutones.removeTropa(tropaGuerrerosDePorraConMejora));
        assertTrue(aldeaTeutones.removeTropa(tropaGuerrerosDePorraSinMejora));
        assertTrue(aldeaTeutones.removeTropa(tropaPaladines));
        assertFalse(aldeaTeutones.removeTropa(tropaPaladines));//Eliminamos tropa ya eliminada
        assertFalse(aldeaTeutones.removeTropa(tropaLegionarios));//Tropa que no pertenece a la aldea
        assertFalse(aldeaTeutones.removeTropa(null));

        assertTrue(aldeaGalos.removeTropa(tropaDruidas));
        assertTrue(aldeaGalos.removeTropa(tropaDruidas));
        assertTrue(aldeaGalos.removeTropa(tropaRayosDeTeutatesConMejora));
        assertTrue(aldeaGalos.removeTropa(tropaRayosDeTeutatesSinMejora));
        assertTrue(aldeaGalos.removeTropa(tropaFalanges));
        assertFalse(aldeaGalos.removeTropa(tropaFalanges));//Eliminamos tropa ya eliminada
        assertFalse(aldeaGalos.removeTropa(tropaLegionarios));//Tropa que no pertenece a la aldea
        assertFalse(aldeaGalos.removeTropa(null));
    }

    @Test
    void testThrows() {
        assertThrows(IllegalArgumentException.class, () -> { new AldeaRomanos("AldeaAnhosNegativos", -1, 35); });//Error de anhos negativo
        assertThrows(IllegalArgumentException.class, () -> { new AldeaRomanos("AldeaAnhosNegativos", 1, -20); });//Error de resistencia negativo
        assertThrows(IllegalArgumentException.class, () -> { aldeaRomanos.setAnhos(-10);});//Error de anhos negativo
        assertThrows(IllegalArgumentException.class, () -> { aldeaRomanos.setResistencia(-33);});//Error de resistencia negativo
    }

    @Test
    void testGetAumentoDefensa() {
        aldeaRomanos.setResistencia(10);
        aldeaRomanos.setAumentoDefensa(1.5);
        double expectedAumentoDefensa = 10 * 1.5;
        assertEquals(expectedAumentoDefensa, aldeaRomanos.getAumentoDefensa());
    }

    @Test
    void testToStringSinTropas() {
        String expectedOutput = "AldeaA have the following soldiery:\n";
        assertEquals(expectedOutput, aldeaRomanos.toString());
    }

    @Test
    void testToStringConTropas() {
        aldeaRomanos.addTropa(tropaLegionarios);
        String expectedOutput = "AldeaA have the following soldiery:\nRomanians Soldiery - Legionary";
        assertEquals(expectedOutput, aldeaRomanos.toString());
    }


    @Test
    void testBatallaGananAtacantes() {
        aldeaTeutones.addTropa(guerrerosDeHacha);
        aldeaTeutones.addTropa(tropaGuerrerosDePorraConMejora);
        aldeaTeutones.addTropa(tropaPaladines);

        aldeaRomanos.addTropa(tropaLegionarios);

        //Array de strings para verificar si la salida es correcta
        List<String> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add("### Starts the battle! --> AldeaB Attacks AldeaA! ###");
        resultadoEsperado.add("AldeaB have the following soldiery:\nTeutons Soldiery - Axeman\n" +
                "Teutons Soldiery - Mazeman with iron maze\nTeutons Soldiery - Paladin");
        resultadoEsperado.add("Total AldeaB attack power: 156\n");
        resultadoEsperado.add("AldeaA have the following soldiery:\nRomanians Soldiery - Legionary");
        resultadoEsperado.add("Total AldeaA defense power: 95");
        resultadoEsperado.add("\nAldeaB with an age of 2 years WINS!");

        List<String> resultadoBatalla = Travian.batalla(aldeaTeutones, aldeaRomanos);
        assertEquals(resultadoEsperado, resultadoBatalla);
    }

    @Test
    void testBatallaGananDefensores() {
        aldeaTeutones.addTropa(guerrerosDeHacha);
        aldeaTeutones.addTropa(tropaGuerrerosDePorraConMejora);
        aldeaTeutones.addTropa(tropaPaladines);

        aldeaGalos.addTropa(tropaDruidas);
        aldeaGalos.addTropa(tropaRayosDeTeutatesConMejora);
        aldeaGalos.addTropa(tropaFalanges);

        //Array de strings para verificar si la salida es correcta
        List<String> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add("### Starts the battle! --> AldeaB Attacks AldeaC! ###");
        resultadoEsperado.add("AldeaB have the following soldiery:\nTeutons Soldiery - Axeman\n" +
                "Teutons Soldiery - Mazeman with iron maze\nTeutons Soldiery - Paladin");
        resultadoEsperado.add("Total AldeaB attack power: 156\n");
        resultadoEsperado.add("AldeaC have the following soldiery:\nGauls Soldiery - Druidrider\n" +
                "Gauls Soldiery - Theutates Thunder with heavy armor\nGauls Soldiery - Phalanx");
        resultadoEsperado.add("Total AldeaC defense power: 321");
        resultadoEsperado.add("\nAldeaC with an age of 3 years WINS!");

        List<String> resultadoBatalla = Travian.batalla(aldeaTeutones, aldeaGalos);
        assertEquals(resultadoEsperado, resultadoBatalla);
    }
}
