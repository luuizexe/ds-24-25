package e1.Estados;

import e1.Buques.Buque;

public class Hundido extends Inactivo {

    private static final Hundido instancia = new Hundido();
    private Hundido() { }
    public static Hundido getInstancia() { return instancia; }

    @Override
    public void hundir(Buque buque) {
        System.out.println(buque + " no se puede volver a hundir.");
    }

    @Override
    public String toString() {
        return "hundido";
    }
}