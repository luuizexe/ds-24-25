package e1.Buques;

public class BuqueUltrapesado extends Buque {
    private static final int RECOMPENSA = 600000;
    private static final int COSTE_REPARACION = 500000;

    public BuqueUltrapesado(String nombre, TipoBuque tipoBuque) {
        super(nombre, tipoBuque, RECOMPENSA, COSTE_REPARACION);
    }
}