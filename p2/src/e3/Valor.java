package e3;

public enum Valor {
    EUR02(200, Color.PLATA),
    EUR01(100, Color.PLATA),
    CENT50(50, Color.ORO),
    CENT20(20, Color.ORO),
    CENT10(10, Color.ORO),
    CENT05(5, Color.BRONCE),
    CENT02(2, Color.BRONCE),
    CENT01(1, Color.BRONCE);

    private final int valor;
    private final Color color;

    Valor(int valor, Color color) {
        this.valor = valor;
        this.color = color;
    }

    public int getValorEnCents() {
        return valor;
    }

    public Color getColor() {
        return color;
    }
}
