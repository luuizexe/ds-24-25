package e1.Buques;

public class BuqueLigero extends Buque {
    // Vale tener una constante por cada tipo de buque o hace falta de cada subtipo
    private static final int RECOMPENSA = 100000;
    private static final int COSTE_REPARACION = 100000;

    public BuqueLigero(String nombre, TipoBuque tipoBuque) {
        super(nombre, tipoBuque, RECOMPENSA, COSTE_REPARACION);
    }
}
