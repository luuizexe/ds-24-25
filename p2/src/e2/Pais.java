package e2;

public enum Pais {
    AD("Andorra"),
    AT("Austria"),
    BE("Belgium"),
    CY("Cyprus"),
    HR("Croatia"),
    EE("Estonia"),
    FI("Finland"),
    FR("France"),
    DE("Germany"),
    GR("Greece"),
    IE("Ireland"),
    IT("Italy"),
    LV("Latvia"),
    LT("Lithuania"),
    LU("Luxembourg"),
    MT("Malta"),
    MC("Monaco"),
    NL("Netherlands"),
    PT("Portugal"),
    SM("San Marino"),
    SK("Slovakia"),
    SI("Slovenia"),
    ES("Spain"),
    VA("Vatican City");

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    // Constructor
    Pais(String nombre) {
        this.nombre = nombre;
    }
}
