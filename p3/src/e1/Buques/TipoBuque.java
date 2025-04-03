package e1.Buques;

public enum TipoBuque {
    DE("Destructores de Escolta"),
    DD("Destructores"),
    CL("Cruceros Ligeros"),
    AV("Portahidros"),
    CA("Cruceros Pesados"),
    CV("Portaaviones"),
    BB("Acorazados");


    private String nombre;

    public String getNombre() {
        return nombre;
    }

    // Constructor
    TipoBuque(String nombre) {
        this.nombre = nombre;
    }
}