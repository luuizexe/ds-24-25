package e1.Estados;

import e1.Buques.Buque;

public class EnBatalla implements Estado {

    private static final EnBatalla instancia = new EnBatalla();
    private EnBatalla() { }
    public static EnBatalla getInstancia() { return instancia; }

    @Override
    public void empezarEjercicio(Buque buque) {
        System.out.println(buque + " ya esta en un ejercicio.");
    }

    @Override
    public void terminarEjercicio(Buque buque) {
        System.out.println(buque + " vuelve a base.");
        buque.setEstado(EnBase.getInstancia());
        buque.misionCompletada();
    }

    @Override
    public void solicitarReparacion(Buque buque) {
        System.out.println(buque + " no puede solicitar reparacion estando en batalla");
    }

    @Override
    public void confirmarReparacion(Buque buque) {
        System.out.println(buque + " no puede confirmar una reparacion estando en batalla.");
    }

    @Override
    public void terminarReparacion(Buque buque) {
        System.out.println(buque + " no esta en reparacion.");
    }

    @Override
    public void cancelarReparacion(Buque buque) {
        System.out.println(buque + " no puede cancelar una reparacion estando en batalla.");
    }

    @Override
    public void desmantelar(Buque buque) {
        System.out.println(buque + " no se puede desmantelar en batalla.");
    }

    @Override
    public void hundir(Buque buque) {
        System.out.println(buque + " se ha hundido.");
        buque.setEstado(Hundido.getInstancia());
    }

    @Override
    public String toString() {
        return "en batalla";
    }
}
