package e1.Buques;

public class BuqueUltraligero extends Buque {
    private static final int RECOMPENSA = 10000;
    private static final int COSTE_REPARACION = 8000;

    public BuqueUltraligero(String nombre, TipoBuque tipoBuque) {
        super(nombre, tipoBuque, RECOMPENSA, COSTE_REPARACION);
    }
}
