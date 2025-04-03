package e1.Aldeas.Teutones;

import e1.Aldeas.Tropa;

public class TropaGuerrerosDeHacha extends Tropa {
    private static final int PUNTOS_ATAQUE = 60;
    private static final int PUNTOS_DEFENSA = 30;
    private static final String NOMBRE_TROPA = "Axeman";

    public TropaGuerrerosDeHacha(AldeaTeutones aldea) {
        super(aldea, (int) (PUNTOS_ATAQUE * aldea.getAumentoAtaque()), (int) (PUNTOS_DEFENSA + aldea.getAumentoDefensa())
                ,NOMBRE_TROPA, false);
    }
}
