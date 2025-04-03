package e1.Aldeas.Romanos;

import e1.Aldeas.Tropa;

public class TropaPretorianos extends Tropa {
    private static final int PUNTOS_ATAQUE = 40;
    private static final int PUNTOS_DEFENSA = 35;
    private static final double AUMENTO_DEFENSA_TROPA = 1.10;
    private static final String NOMBRE_TROPA = "Praetorian";
    private static final String NOMBRE_OBJETO = "armor";

    public TropaPretorianos(AldeaRomanos aldea, boolean mejora) {
        super(aldea, (int) (PUNTOS_ATAQUE * aldea.getAumentoAtaque())
                , (int) ((mejora ? (PUNTOS_DEFENSA * AUMENTO_DEFENSA_TROPA) : PUNTOS_DEFENSA) + aldea.getAumentoDefensa())
                ,NOMBRE_TROPA, NOMBRE_OBJETO, mejora);
    }
}
