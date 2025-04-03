package e1.Aldeas.Teutones;

import e1.Aldeas.Aldea;

public class AldeaTeutones extends Aldea {
    private static final double AUMENTO_ATAQUE_ALDEA = 0.95;
    private static final double AUMENTO_DEFENSA_ALDEA = 1;
    private static final String TIPO_ALDEA = "Teutons";

    public AldeaTeutones(String nombre, int anhos, int resistencia) {
        super(nombre, TIPO_ALDEA, anhos, resistencia);
        this.setAumentoAtaque(AUMENTO_ATAQUE_ALDEA);
        this.setAumentoDefensa(AUMENTO_DEFENSA_ALDEA);
    };
}
