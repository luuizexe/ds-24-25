package e2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MercadoTest {
    private static Accion apple, nvidia, amd;
    private static FactoriaCliente factoria;
    private static Cliente clienteSencillo, clienteDetallado, clienteVolumen, clienteDefault;

    @BeforeEach
    void setUp() {
        InfoAccion infoApple = new InfoAccion("AAPL", 4.0, 5.0, 3.0, 200);
        apple = new Accion(infoApple);

        // Este va a recortar el simbolo a 4 caracteres y lo va a poner en mayusculas
        InfoAccion infoNvidia = new InfoAccion("nvidia", 100.6, 123.4, 70.7, 500);
        nvidia = new Accion(infoNvidia);

        InfoAccion infoAmd = new InfoAccion("AMD", 80.1, 110.0, 60.3, 350);
        amd = new Accion(infoAmd);

        factoria = new FactoriaCliente();

        clienteSencillo = factoria.createCliente("sencillo");
        clienteDetallado = factoria.createCliente("detallado");
        clienteVolumen = factoria.createCliente("volumen");
        // Si no se pone ningun tipo conocido, por default se va a crear un cliente sencillo
        clienteDefault = factoria.createCliente("test");

        // Apple solo tiene clientes sencillos
        apple.insertarCliente(clienteSencillo);
        apple.insertarCliente(clienteDefault);

        // Nvidia tiene muchos clientes
        nvidia.insertarCliente(clienteSencillo);
        nvidia.insertarCliente(clienteDetallado);
        nvidia.insertarCliente(clienteVolumen);
        nvidia.insertarCliente(clienteDefault);

        // Amd solo tiene clientes detallados
        amd.insertarCliente(clienteVolumen);
    }

    @Test
    public void testGettersInfoAccion() {
        InfoAccion info = new InfoAccion("TEST", 100.3, 170.0, 93.4, 25000);

        // Simplemente para comprobar que se guardan bien los datos de la accion
        Assertions.assertEquals("TEST", info.getSimbolo());
        Assertions.assertEquals(100.3, info.getCierre());
        Assertions.assertEquals(170.0, info.getMaximo());
        Assertions.assertEquals(93.4, info.getMinimo());
        Assertions.assertEquals(25000, info.getVolumen());
    }

    @Test
    public void testAccionesIncorrectas() {
        // Simbolo nulo
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new InfoAccion(null, 100, 120, 90, 1020);
        });

        // Simbolo vacio
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new InfoAccion("", 100, 120, 90, 1020);
        });

        // Cierre negativo
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new InfoAccion("TEST", -100, 120, 90, 1020);
        });
        // Maximo negativo
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new InfoAccion("TEST", 100, -120, 90, 1020);
        });
        // Minimo negativo
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new InfoAccion("TEST", 100, 120, -90, 1020);
        });
        // Volumen negativo
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new InfoAccion("TEST", 100, 120, 90, -1020);
        });

        // Minimo mayor que cierre
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new InfoAccion("TEST", 100, 120, 110, 1020);
        });

        // Maximo menor que cierre
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new InfoAccion("TEST", 100, 95, 90, 1020);
        });
    }

    @Test
    public void testEliminarCliente() {
        // Apple tiene dos clientes sencillos
        Assertions.assertEquals(2, apple.getClientes().size());
        apple.eliminarCliente(clienteSencillo);
        Assertions.assertEquals(1, apple.getClientes().size());
        apple.eliminarCliente(clienteDefault);
        Assertions.assertEquals(0, apple.getClientes().size());
    }

    @Test
    public void testNotificarClientes() {
        // Vamos a hacer una accion sin clientes
        Accion accionMala = new Accion(null);
        InfoAccion infoNoClientes = new InfoAccion("TEST", 100, 110, 50, 1002);
        List<String> salida = new ArrayList<>();
        salida = accionMala.setInfoAccion(infoNoClientes);
        Assertions.assertTrue(salida.isEmpty());


        // Creamos nueva accion para actualizar los datos de apple a los 2 clientes sencillos interesados
        InfoAccion infoNuevaApple = new InfoAccion("APPL", 110, 130.3, 80.2, 100);
        salida = apple.setInfoAccion(infoNuevaApple);
        Assertions.assertEquals(2, salida.size());

        // Cambio solo el volumen para notificar al que hay de ClienteVolumen mas el ClienteDetallado
        InfoAccion infoNuevaNvidia = new InfoAccion("NVID", 100.6, 123.4, 70.7, 600);
        salida = nvidia.setInfoAccion(infoNuevaNvidia);
        Assertions.assertEquals(2, salida.size());

        // Ahora cambio los datos menos el simbolo
        infoNuevaNvidia = new InfoAccion("NVID", 110.6, 133.4, 72.7, 620);
        salida = nvidia.setInfoAccion(infoNuevaNvidia);
        Assertions.assertEquals(4, salida.size());

        // A continuacion, probamos a no cambiar nada en la accion de nvidia, no se deberia notificar a nadie
        InfoAccion infoNvidiaNoNueva = new InfoAccion("NVID", 110.6, 133.4, 72.7, 620);
        salida = nvidia.setInfoAccion(infoNvidiaNoNueva);
        Assertions.assertEquals(0, salida.size());

        // A continuacion, cambiamos cierre pero no notifica ya que el unico cliente que sigue amd le interesa el volumen
        InfoAccion infoAmdNoNueva =  new InfoAccion("AMD", 81.1, 110.0, 60.3, 350);
        salida = amd.setInfoAccion(infoAmdNoNueva);
        Assertions.assertEquals(0, salida.size());

        // Por ultimo, notificamos cambiando el volumen de AMD para su cliente interesado
        InfoAccion infoAmdNueva =  new InfoAccion("AMD", 81.1, 110.0, 60.3, 370);
        salida = amd.setInfoAccion(infoAmdNueva);
        Assertions.assertEquals(1, salida.size());
    }
}
