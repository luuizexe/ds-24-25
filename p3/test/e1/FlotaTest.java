package e1;


import e1.Buques.Buque;
import e1.Buques.TipoBuque;
import e1.Estados.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlotaTest {

    // Las misiones al meter buques debe ser siempre 0 o damos la opcion de poner las que queramos
    private static Flota flota;
    private static FactoriaBuque factoria;
    private static Buque ultraLigeroDE, ultraLigeroDD, ligeroCL, ligeroAV, pesadoCA, pesadoCV, ultraPesadoBB, buqueFalso;

    @BeforeEach
    void setUp() {
        flota = new Flota();
        factoria = new FactoriaBuque();

        // Creamos varios buques con nuestra factoria y los anadimos en la flota.
        //Por defecto, todos estaran en base
        ultraLigeroDE = factoria.createBuque("UltraligeroDE", TipoBuque.DE);
        ultraLigeroDD = factoria.createBuque("UltraligeroDD", TipoBuque.DD);
        ligeroCL = factoria.createBuque("LigeroCL", TipoBuque.CL);
        ligeroAV = factoria.createBuque("LigeroAV", TipoBuque.AV);
        pesadoCA = factoria.createBuque("PesadoCA", TipoBuque.CA);
        pesadoCV = factoria.createBuque("PesadoCV", TipoBuque.CV);
        ultraPesadoBB = factoria.createBuque("ultraPesadoBB", TipoBuque.BB);

        flota.addBuque(ultraLigeroDE);
        flota.addBuque(ultraLigeroDD);
        flota.addBuque(ligeroCL);
        flota.addBuque(ligeroAV);
        flota.addBuque(pesadoCA);
        flota.addBuque(pesadoCV);
        flota.addBuque(ultraPesadoBB);
    }

    @Test
    void testBuquesEnLista() {
        //Verificamos que todos los buques estan en nuestra flota
        assertTrue(flota.getBuques().contains(ultraLigeroDE));
        assertTrue(flota.getBuques().contains(ultraLigeroDD));
        assertTrue(flota.getBuques().contains(ligeroCL));
        assertTrue(flota.getBuques().contains(ligeroAV));
        assertTrue(flota.getBuques().contains(pesadoCA));
        assertTrue(flota.getBuques().contains(pesadoCV));
        assertTrue(flota.getBuques().contains(ultraPesadoBB));
    }

    @Test
    void testEstados() {
        //Por defecto, todos los buques empiezan en base

        assertInstanceOf(EnBase.class, ultraLigeroDE.getEstado());
        //Enviamos a un ultraligero  a batalla
        flota.comenzarEjercicioBuque(ultraLigeroDE);
        assertInstanceOf(EnBatalla.class, ultraLigeroDE.getEstado());
        //Vuelve a base con su recompensa (+10000)
        flota.cobrarRecompensa(ultraLigeroDE);
        assertInstanceOf(EnBase.class, ultraLigeroDE.getEstado());
        //Pedimos una reparacion
        flota.pedirReparacion(ultraLigeroDE);
        assertInstanceOf(PendienteReparacion.class, ultraLigeroDE.getEstado());
        //Lo reparamos y como es express, vuelve inmediatamente a base y sale gratis
        flota.terminarReparacion(ultraLigeroDE);
        assertInstanceOf(EnBase.class, ultraLigeroDE.getEstado());
        //Verificamos los ingresos/fondos de la batalla
        //Reparacion express es gratis
        assertEquals(10000, flota.getIngresos());
        assertEquals(10000, flota.getFondos());


        //Enviamos a un ultrapesado a batalla
        flota.comenzarEjercicioBuque(ultraPesadoBB);
        assertInstanceOf(EnBatalla.class, ultraPesadoBB.getEstado());
        //Vuelve a base con su recompensa (+600000)
        flota.cobrarRecompensa(ultraPesadoBB);
        assertInstanceOf(EnBase.class, ultraPesadoBB.getEstado());
        assertEquals(610000, flota.getFondos());
        //Pedimos una reparacion a un ultrapesado y ultraligero
        flota.pedirReparacion(ultraPesadoBB);
        flota.pedirReparacion(ultraLigeroDD);
        assertInstanceOf(PendienteReparacion.class, ultraPesadoBB.getEstado());
        assertInstanceOf(PendienteReparacion.class, ultraLigeroDD.getEstado());
        //Enviamos a reparar al ultrapesado (-500000) e intentamos hacerlo tmb con el ultraligero
        flota.confirmarReparacion(ultraPesadoBB);
        flota.terminarReparacion(ultraLigeroDD);
        // Como el ultrapesado se esta reparando, el ultraligero no va a poder repararse
        assertInstanceOf(EnReparacion.class, ultraPesadoBB.getEstado());
        assertInstanceOf(PendienteReparacion.class, ultraLigeroDD.getEstado());
        //El ultraligero ya estaba pendiente de reparacion
        flota.pedirReparacion(ultraLigeroDD);
        //Lo mandamos a reparar (-8000) sin ser express
        flota.confirmarReparacion(ultraLigeroDD);
        assertInstanceOf(EnReparacion.class, ultraLigeroDD.getEstado());
        //Verificamos fondos/gastos
        assertEquals(102000, flota.getFondos());
        assertEquals(508000, flota.getGastos());
        //Terminamos las reparaciones del ultrapesado y cancelamos la del ultraligero
        flota.terminarReparacion(ultraPesadoBB);
        flota.cancelarReparacion(ultraLigeroDD);
        //El ultrapseado queda en base reparado y el ultraligero pendiente de reparacion
        assertInstanceOf(EnBase.class, ultraPesadoBB.getEstado());
        assertInstanceOf(PendienteReparacion.class, ultraLigeroDD.getEstado());

        // Enviamos a un ligero a batalla
        flota.comenzarEjercicioBuque(ligeroCL);
        assertInstanceOf(EnBatalla.class, ligeroCL.getEstado());
        //Lo hundimos
        flota.hundirBuque(ligeroCL);
        assertInstanceOf(Hundido.class, ligeroCL.getEstado());
        //No podemos cobrar su recompensa porque esta hundido
        flota.cobrarRecompensa(ligeroCL);
        assertInstanceOf(Hundido.class, ligeroCL.getEstado());
        assertEquals(102000, flota.getFondos());

        // Enviamos a un pesado a batalla
        flota.comenzarEjercicioBuque(pesadoCV);
        assertInstanceOf(EnBatalla.class, pesadoCV.getEstado());
        //Cobramos su recompensa (+200000)
        flota.cobrarRecompensa(pesadoCV);
        assertInstanceOf(EnBase.class, pesadoCV.getEstado());
        //Pedimos una reparacion
        flota.pedirReparacion(pesadoCV);
        assertInstanceOf(PendienteReparacion.class, pesadoCV.getEstado());
        //No lo reparamos, lo desmantelamos (pasa a estar obsoleto)
        flota.desmantelarBuque(pesadoCV);
        assertInstanceOf(Obsoleto.class, pesadoCV.getEstado());
        //No podemos hundirlo porque esta obsoleto
        flota.hundirBuque(pesadoCV);
        assertInstanceOf(Obsoleto.class, pesadoCV.getEstado());
        assertEquals(302000, flota.getFondos());

        //Enviamos a un ultrapesado a batalla
        flota.comenzarEjercicioBuque(ultraPesadoBB);
        assertInstanceOf(EnBatalla.class, ultraPesadoBB.getEstado());
        //Cobramos su recompensa (+600000)
        flota.cobrarRecompensa(ultraPesadoBB);
        assertEquals(902000, flota.getFondos());
        assertInstanceOf(EnBase.class, ultraPesadoBB.getEstado());
        //Pedimos su reparacion, por lo que pasa a estar deteriorado
        flota.pedirReparacion(ultraPesadoBB);
        assertTrue(ultraPesadoBB.isDeteriorado());
        assertInstanceOf(PendienteReparacion.class, ultraPesadoBB.getEstado());
        //No lo reparamos, lo cancelamos y seguira deteriorado
        flota.cancelarReparacion(ultraPesadoBB);
        assertInstanceOf(EnBase.class, ultraPesadoBB.getEstado());
        assertTrue(ultraPesadoBB.isDeteriorado());
        assertEquals(902000, flota.getFondos());
        //Volvemos a pedir su reparacion
        flota.pedirReparacion(ultraPesadoBB);
        assertInstanceOf(PendienteReparacion.class, ultraPesadoBB.getEstado());
        //La confirmamos (-500000)
        flota.confirmarReparacion(ultraPesadoBB);
        assertInstanceOf(EnReparacion.class, ultraPesadoBB.getEstado());
        assertEquals(402000, flota.getFondos());
        assertTrue(ultraPesadoBB.isDeteriorado());
        //Cuando termine dicha reparacion, dejara de estar deteriorado
        flota.terminarReparacion(ultraPesadoBB);
        assertInstanceOf(EnBase.class, ultraPesadoBB.getEstado());
        assertFalse(ultraPesadoBB.isDeteriorado());


        // Pruebas con un buque fuera de la lista
        buqueFalso = factoria.createBuque("buqueFalso", TipoBuque.BB);
        // Como no esta en la lista, no puede hacer nada, por lo que su estado y los fondos no cambia
        //No puede ir a batalla
        flota.comenzarEjercicioBuque(buqueFalso);
        assertInstanceOf(EnBase.class, buqueFalso.getEstado());
        // Ni ser hundido
        flota.hundirBuque(buqueFalso);
        assertInstanceOf(EnBase.class, buqueFalso.getEstado());
        //Ni cobrar su recompensa
        flota.cobrarRecompensa(buqueFalso);
        assertEquals(402000, flota.getFondos());
        //Ni pedir reparacion
        flota.pedirReparacion(buqueFalso);
        assertInstanceOf(EnBase.class, buqueFalso.getEstado());
        //Ni confirmarla
        flota.confirmarReparacion(buqueFalso);
        assertEquals(402000, flota.getFondos());
        //Ni ser desmantelado
        flota.desmantelarBuque(buqueFalso);
        assertInstanceOf(EnBase.class, buqueFalso.getEstado());
    }

    @Test
    void testListadoActivosInactivos() {
        List<String> salidaActivos = new ArrayList<>();
        List<String> salidaInactivos = new ArrayList<>();
        //Estado inicial:
        /*
        ultraLigeroDE       en batalla (activo)
        ultraLigeroDD       hundido (inactivo)
        ligeroCL            hundido (inactivo)
        ligeroAV            en base (activo)
        pesadoCA            en reparacion (activo)
        pesadoCV            obsoleto (inactivo)
        ultraPesadoBB       pendiente de reparacion (activo)
         */

        flota.comenzarEjercicioBuque(ultraLigeroDE);
        assertInstanceOf(EnBatalla.class, ultraLigeroDE.getEstado());

        flota.comenzarEjercicioBuque(ultraLigeroDD);
        flota.hundirBuque(ultraLigeroDD);
        assertInstanceOf(Hundido.class, ultraLigeroDD.getEstado());

        flota.comenzarEjercicioBuque(ligeroCL);
        flota.hundirBuque(ligeroCL);
        assertInstanceOf(Hundido.class, ligeroCL.getEstado());

        flota.desmantelarBuque(pesadoCV);
        assertInstanceOf(Obsoleto.class, pesadoCV.getEstado());

        flota.comenzarEjercicioBuque(ultraPesadoBB);
        flota.cobrarRecompensa(ultraPesadoBB);
        flota.pedirReparacion(ultraPesadoBB);
        assertInstanceOf(PendienteReparacion.class, ultraPesadoBB.getEstado());

        flota.pedirReparacion(pesadoCA);
        flota.confirmarReparacion(pesadoCA);
        assertInstanceOf(EnReparacion.class, pesadoCA.getEstado());

        salidaActivos.add("Buques activos: \n ------------------------------------------------------------");
        salidaActivos.add("Nombre: " + ultraLigeroDE + " | Estado: " + ultraLigeroDE.getEstado() + " | Misiones: " + ultraLigeroDE.getMisiones());
        salidaActivos.add("Nombre: " + ligeroAV + " | Estado: " + ligeroAV.getEstado() + " | Misiones: " + ligeroAV.getMisiones());
        salidaActivos.add("Nombre: " + pesadoCA + " | Estado: " + pesadoCA.getEstado() + " | Misiones: " + pesadoCA.getMisiones());
        salidaActivos.add("Nombre: " + ultraPesadoBB + " | Estado: " + ultraPesadoBB.getEstado() + " | Misiones: " + ultraPesadoBB.getMisiones());
        assertEquals(flota.listadoActivos(), salidaActivos);

        salidaInactivos.add("Buques inactivos: \n ------------------------------------------------------------");
        salidaInactivos.add("Nombre: " + ultraLigeroDD + " | Razones: " + ultraLigeroDD.getEstado() + " | Misiones: " + ultraLigeroDD.getMisiones());
        salidaInactivos.add("Nombre: " + ligeroCL + " | Razones: " + ligeroCL.getEstado() + " | Misiones: " + ligeroCL.getMisiones());
        salidaInactivos.add("Nombre: " + pesadoCV + " | Razones: " + pesadoCV.getEstado() + " | Misiones: " + pesadoCV.getMisiones());
        assertEquals(flota.listadoInactivos(), salidaInactivos);
    }

    @Test
    void testListadoMovimientos() {
        List<String> salida = new ArrayList<>();
    /*
        ultraLigeroDD       pendiente de reparacion
        ligeroCL            en reparacion
        pesadoCV            en batalla
        ultraPesadoBB       pendiente de reparacion
    */

        flota.comenzarEjercicioBuque(pesadoCV);//+200000 previstos
        assertInstanceOf(EnBatalla.class, pesadoCV.getEstado());

        flota.comenzarEjercicioBuque(ligeroCL);
        flota.cobrarRecompensa(ligeroCL); //+100000
        flota.pedirReparacion(ligeroCL);
        flota.confirmarReparacion(ligeroCL);//-100000
        assertInstanceOf(EnReparacion.class, ligeroCL.getEstado());

        flota.comenzarEjercicioBuque(ultraLigeroDD);
        flota.cobrarRecompensa(ultraLigeroDD);//+10000
        flota.pedirReparacion(ultraLigeroDD);//-8000 previstos
        assertInstanceOf(PendienteReparacion.class, ultraLigeroDD.getEstado());

        flota.comenzarEjercicioBuque(ultraPesadoBB);
        flota.cobrarRecompensa(ultraPesadoBB);//+600000
        flota.pedirReparacion(ultraPesadoBB);//-500000 previstos
        assertInstanceOf(PendienteReparacion.class, ultraPesadoBB.getEstado());

        salida.add("Cuentas de la flota: \n ------------------------------------------------------------");
        salida.add("Fondos disponibles: " + flota.getFondos());
        salida.add("Historial de movimientos: ");
        salida.addAll(flota.getMovimientosCuenta());
        salida.add("Ingresos totales: " + flota.getIngresos());
        salida.add("Gastos totales: " + flota.getGastos());
        salida.add("Reparaciones pendientes:");
        salida.add(ultraLigeroDD + " necesita una reparacion que cuesta " + ultraLigeroDD.getCosteReparacion() + " euros.");
        salida.add(ligeroCL + " realizando una reparacion que cuesta " + ligeroCL.getCosteReparacion() + " euros.");
        salida.add(ultraPesadoBB + " necesita una reparacion que cuesta " + ultraPesadoBB.getCosteReparacion() + " euros.");
        salida.add("Ingresos previstos: 200000");
        salida.add("Gastos previstos: 508000");
        assertEquals(flota.estadoCuentas(), salida);
    }

    @Test
    void testEstadosImposibles() {
        // Aqui probamos las llamadas a los estados que no se deberian producir.
        // Entre medias tenemos algunas llamadas legales (las de flota), para luego probar operaciones no permitidas
        
        //En base solo se puede ir a batalla, desmantelar o pedir una reparacion
        assertInstanceOf(EnBase.class, ultraPesadoBB.getEstado());
        ultraPesadoBB.getEstado().terminarReparacion(ultraPesadoBB);
        ultraPesadoBB.getEstado().cancelarReparacion(ultraPesadoBB);
        ultraPesadoBB.getEstado().confirmarReparacion(ultraPesadoBB);
        ultraPesadoBB.getEstado().terminarEjercicio(ultraPesadoBB);
        ultraPesadoBB.getEstado().hundir(ultraPesadoBB);
        assertInstanceOf(EnBase.class, ultraPesadoBB.getEstado());
        
        flota.comenzarEjercicioBuque(ultraPesadoBB);
        assertInstanceOf(EnBatalla.class, ultraPesadoBB.getEstado());
        //En batalla solo se puede hundir o volver a base(cobrar recompensa)
        ultraPesadoBB.getEstado().empezarEjercicio(ultraPesadoBB);
        ultraPesadoBB.getEstado().solicitarReparacion(ultraPesadoBB);
        ultraPesadoBB.getEstado().confirmarReparacion(ultraPesadoBB);
        ultraPesadoBB.getEstado().terminarReparacion(ultraPesadoBB);
        ultraPesadoBB.getEstado().cancelarReparacion(ultraPesadoBB);
        ultraPesadoBB.getEstado().desmantelar(ultraPesadoBB);
        assertInstanceOf(EnBatalla.class, ultraPesadoBB.getEstado());
        
        flota.cobrarRecompensa(ultraPesadoBB);
        assertInstanceOf(EnBase.class, ultraPesadoBB.getEstado());
        flota.pedirReparacion(ultraPesadoBB);
        assertInstanceOf(PendienteReparacion.class, ultraPesadoBB.getEstado());
        //Si esta pendiente de reparacion solo se puede confirmar su reparacion, cancelarla o desmantelarlo
        //Solo en el caso de los ultraligeros, tambien podemos repararlos y devolverlos a base de inmediato
        ultraPesadoBB.getEstado().empezarEjercicio(ultraPesadoBB);
        ultraPesadoBB.getEstado().solicitarReparacion(ultraPesadoBB);
        ultraPesadoBB.getEstado().hundir(ultraPesadoBB);
        assertInstanceOf(PendienteReparacion.class, ultraPesadoBB.getEstado());

        flota.desmantelarBuque(ultraPesadoBB);
        assertInstanceOf(Obsoleto.class, ultraPesadoBB.getEstado());
        //Si esta desmantelado no puede hacer nada
        ultraPesadoBB.getEstado().empezarEjercicio(ultraPesadoBB);
        ultraPesadoBB.getEstado().terminarEjercicio(ultraPesadoBB);
        ultraPesadoBB.getEstado().solicitarReparacion(ultraPesadoBB);
        ultraPesadoBB.getEstado().confirmarReparacion(ultraPesadoBB);
        ultraPesadoBB.getEstado().terminarReparacion(ultraPesadoBB);
        ultraPesadoBB.getEstado().cancelarReparacion(ultraPesadoBB);
        ultraPesadoBB.getEstado().desmantelar(ultraPesadoBB);
        ultraPesadoBB.getEstado().hundir(ultraPesadoBB);
        assertInstanceOf(Obsoleto.class, ultraPesadoBB.getEstado());

        flota.comenzarEjercicioBuque(ligeroCL);
        flota.hundirBuque(ligeroCL);
        assertInstanceOf(Hundido.class, ligeroCL.getEstado());
        //Si esta hundido tampoco podemos hacer nada
        ligeroCL.getEstado().empezarEjercicio(ligeroCL);
        ligeroCL.getEstado().terminarEjercicio(ligeroCL);
        ligeroCL.getEstado().solicitarReparacion(ligeroCL);
        ligeroCL.getEstado().confirmarReparacion(ligeroCL);
        ligeroCL.getEstado().terminarReparacion(ligeroCL);
        ligeroCL.getEstado().cancelarReparacion(ligeroCL);
        ligeroCL.getEstado().desmantelar(ligeroCL);
        ligeroCL.getEstado().hundir(ligeroCL);
        assertInstanceOf(Hundido.class, ligeroCL.getEstado());

        flota.pedirReparacion(pesadoCV);
        flota.confirmarReparacion(pesadoCV);
        assertInstanceOf(EnReparacion.class, pesadoCV.getEstado());
        //Si esta en reparacion solo podemos terminarla o cancelarla y que vuelva a estar pendiente de reparacion
        pesadoCV.getEstado().empezarEjercicio(pesadoCV);
        pesadoCV.getEstado().terminarEjercicio(pesadoCV);
        pesadoCV.getEstado().solicitarReparacion(pesadoCV);
        pesadoCV.getEstado().confirmarReparacion(pesadoCV);
        pesadoCV.getEstado().desmantelar(pesadoCV);
        pesadoCV.getEstado().hundir(pesadoCV);
        assertInstanceOf(EnReparacion.class, pesadoCV.getEstado());

    }
}
