package e1.Buques;

public class BuquePesado extends Buque {
    private static final int RECOMPENSA = 200000;
    private static final int COSTE_REPARACION = 250000;

    public BuquePesado(String nombre, TipoBuque tipoBuque) {
        super(nombre, tipoBuque, RECOMPENSA, COSTE_REPARACION);
    }
}
