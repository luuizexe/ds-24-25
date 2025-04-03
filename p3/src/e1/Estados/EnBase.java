package e1.Estados;

import e1.Buques.Buque;

public class EnBase implements Estado {
    private static final EnBase instancia = new EnBase();
    private EnBase() { }
    public static EnBase getInstancia() { return instancia; }

    @Override
    public void empezarEjercicio(Buque buque) {
        System.out.println(buque + " ha iniciado un ejercicio.");
        buque.setEstado(EnBatalla.getInstancia());
    }

    @Override
    public void solicitarReparacion(Buque buque) {
        if(!buque.isDeteriorado()) {
            System.out.println(buque + " ha solicitado una reparacion | Coste de la reparacion: " + buque.getCosteReparacion());
            buque.setDeteriorado(true);
            buque.setEstado(PendienteReparacion.getInstancia());
        } else {
            System.out.println(buque + " ya ha solicitado reparacion anteriormente | Coste de la reparacion: " + buque.getCosteReparacion());
            buque.setEstado(PendienteReparacion.getInstancia());
        }
    }

    @Override
    public void confirmarReparacion(Buque buque) {
        System.out.println(buque + " no esta en reparacion.");
    }

    @Override
    public void terminarReparacion(Buque buque) {
        System.out.println(buque + " no esta en reparacion.");
    }

    @Override
    public void cancelarReparacion(Buque buque) {
        System.out.println(buque + " no puede cancelar una reparacion que no se esta haciendo.");
    }

    @Override
    public void terminarEjercicio(Buque buque) {
        System.out.println(buque + " esta en base.");
    }

    @Override
    public void desmantelar(Buque buque) {
        System.out.println(buque + " ha sido desmantelado.");
        buque.setEstado(Obsoleto.getInstancia());
    }

    @Override
    public void hundir(Buque buque) {
        System.out.println(buque + " no se puede hundir en base.");
    }

    @Override
    public String toString() {
        return "en base";
    }
}
