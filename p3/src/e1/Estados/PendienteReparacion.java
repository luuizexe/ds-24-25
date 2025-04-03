package e1.Estados;

import e1.Buques.Buque;

public class PendienteReparacion implements Estado {
    private static final PendienteReparacion instancia = new PendienteReparacion();
    private PendienteReparacion() { }
    public static PendienteReparacion getInstancia() { return instancia; }

    @Override
    public void empezarEjercicio(Buque buque) {
        System.out.println(buque + " no puede empezar un ejercicio estando pendiente de reparacion.");
    }

    @Override
    public void terminarEjercicio(Buque buque) {
        System.out.println(buque + " no puede terminar un ejercicio estando pendiente de reparacion.");
    }

    @Override
    public void solicitarReparacion(Buque buque) {
        System.out.println(buque + " ya ha solicitado la reparacion.");
    }

    @Override
    public void confirmarReparacion(Buque buque) {
        System.out.println(buque + " ha comenzado a repararse.");
        buque.setEstado(EnReparacion.getInstancia());
    }

    @Override
    public void terminarReparacion(Buque buque) {
        System.out.println(buque + " ha terminado su reparacion express.");
        buque.setDeteriorado(false);
        buque.setEstado(EnBase.getInstancia());
    }

    @Override
    public void cancelarReparacion(Buque buque) {
        System.out.println(buque + " ha cancelado su reparacion, vuelve a base pero sigue algo deteriorado.");
        buque.setEstado(EnBase.getInstancia());
    }

    @Override
    public void desmantelar(Buque buque) {
        System.out.println(buque + " ha sido desmantelado.");
        buque.setEstado(Obsoleto.getInstancia());
    }

    @Override
    public void hundir(Buque buque) {
        System.out.println(buque + " no se puede hundir estando pendiente de reparacion.");
    }

    @Override
    public String toString() {
        return "pendiente de reparacion";
    }
}
