package e1.Estados;

import e1.Buques.Buque;

public abstract class Inactivo implements Estado {

    @Override
    public void empezarEjercicio(Buque buque) {
        System.out.println(buque + " no puede realizar ejercicios estando inactivo.");
    }

    @Override
    public void terminarEjercicio(Buque buque) {
        System.out.println(buque + " no puede terminar ejercicios estando inactivo.");
    }

    @Override
    public void solicitarReparacion(Buque buque) {
        System.out.println(buque + " no puede solicitar reparacion estando inactivo.");
    }

    @Override
    public void confirmarReparacion(Buque buque) {
        System.out.println(buque + " no puede confirmar una reparacion estando inactivo.");
    }

    @Override
    public void terminarReparacion(Buque buque) {
        System.out.println(buque + " no puede terminar reparacion estando inactivo.");
    }

    @Override
    public void cancelarReparacion(Buque buque) {
        System.out.println(buque + " no puede cancelar una reparacion estando inactivo.");
    }

    @Override
    public void desmantelar(Buque buque) {
        System.out.println(buque + " no puede desmantelarse estando inactivo.");
    }

}