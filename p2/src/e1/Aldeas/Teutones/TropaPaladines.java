package e1.Aldeas.Teutones;

import e1.Aldeas.Tropa;

public class TropaPaladines extends Tropa {
    private static final int PUNTOS_ATAQUE = 55;
    private static final int PUNTOS_DEFENSA = 100;
    private static final String NOMBRE_TROPA = "Paladin";

    public TropaPaladines(AldeaTeutones aldea) {
        super(aldea, (int) (PUNTOS_ATAQUE * aldea.getAumentoAtaque()), (int) (PUNTOS_DEFENSA + aldea.getAumentoDefensa())
                ,NOMBRE_TROPA, false);
    }
}
