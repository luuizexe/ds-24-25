package e1.Estados;

import e1.Buques.Buque;

public class Obsoleto extends Inactivo {

    private static final Obsoleto instancia = new Obsoleto();
    private Obsoleto() { }
    public static Obsoleto getInstancia() { return instancia; }

    @Override
    public void hundir(Buque buque) {
        System.out.println(buque + " no se puede hundir estando obsoleto.");
    }

    @Override
    public String toString() {
        return "obsoleto";
    }
}