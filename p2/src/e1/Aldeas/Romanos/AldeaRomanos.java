package e1.Aldeas.Romanos;

import e1.Aldeas.Aldea;

public class AldeaRomanos extends Aldea {
    private static final double AUMENTO_ATAQUE_ALDEA = 1.10;
    private static final double AUMENTO_DEFENSA_ALDEA = 2;
    private static final String TIPO_ALDEA = "Romanians";

    public AldeaRomanos(String nombre, int anhos, int resistencia) {
        super(nombre, TIPO_ALDEA, anhos, resistencia);
        this.setAumentoAtaque(AUMENTO_ATAQUE_ALDEA);
        this.setAumentoDefensa(AUMENTO_DEFENSA_ALDEA);
    }
}
