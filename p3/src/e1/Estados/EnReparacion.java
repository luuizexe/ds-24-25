package e1.Estados;

import e1.Buques.Buque;

public class EnReparacion implements Estado {

    private static final EnReparacion instancia = new EnReparacion();
    private EnReparacion() { }
    public static EnReparacion getInstancia() { return instancia; }

    @Override
    public void empezarEjercicio(Buque buque) {
        System.out.println(buque + " no puede comenzar un ejercicio al estar en reparacion.");
    }

    @Override
    public void terminarEjercicio(Buque buque) {
        System.out.println(buque + " no puede terminar un ejercicio al estar en reparacion.");
    }

    @Override
    public void solicitarReparacion(Buque buque) {
        System.out.println(buque + " no puede solicitar un reparacion porque ya esta en una.");
    }

    @Override
    public void confirmarReparacion(Buque buque) {
        System.out.println(buque + " ya esta en reparacion.");
    }

    @Override
    public void terminarReparacion(Buque buque) {
        System.out.println(buque + " ha terminado su reparacion.");
        buque.setDeteriorado(false);
        buque.setEstado(EnBase.getInstancia());
    }

    @Override
    public void cancelarReparacion(Buque buque) {
        System.out.println(buque + " ha cancelado su reparacion, vuelve a la cola de pendientes de reparacion.");
        buque.setEstado(PendienteReparacion.getInstancia());
    }

    @Override
    public void desmantelar(Buque buque) {
        System.out.println(buque + " no se puede desmantelar estando en reparacion.");
    }

    @Override
    public void hundir(Buque buque) {
        System.out.println(buque + " no se puede hundir estando en reparacion.");
    }

    @Override
    public String toString() {
        return "en reparacion";
    }
}