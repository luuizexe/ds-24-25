package e1.Aldeas.Galos;

import e1.Aldeas.Aldea;

public class AldeaGalos extends Aldea {
    private static final double AUMENTO_ATAQUE_ALDEA = 1.20;
    private static final double AUMENTO_DEFENSA_ALDEA = 1.5;
    private static final String TIPO_ALDEA = "Gauls";

    public AldeaGalos(String nombre, int anhos, int resistencia) {
        super(nombre, TIPO_ALDEA, anhos, resistencia);
        this.setAumentoAtaque(AUMENTO_ATAQUE_ALDEA);
        this.setAumentoDefensa(AUMENTO_DEFENSA_ALDEA);
    }
}