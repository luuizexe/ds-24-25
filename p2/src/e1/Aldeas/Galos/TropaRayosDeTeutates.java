package e1.Aldeas.Galos;

import e1.Aldeas.Tropa;

public class TropaRayosDeTeutates extends Tropa {
    private static final int PUNTOS_ATAQUE = 100;
    private static final int PUNTOS_DEFENSA = 25;
    private static final double AUMENTO_ATAQUE_TROPA = 0.75;
    private static final double AUMENTO_DEFENSA_TROPA = 1.25;
    private static final String NOMBRE_TROPA = "Theutates Thunder";
    private static final String NOMBRE_OBJETO = "heavy armor";

    public TropaRayosDeTeutates(AldeaGalos aldea, boolean mejora) {
        super(aldea, (int) ((mejora ? (PUNTOS_ATAQUE * AUMENTO_ATAQUE_TROPA) : PUNTOS_ATAQUE) * aldea.getAumentoAtaque()),
                (int)((mejora ? (PUNTOS_DEFENSA * AUMENTO_DEFENSA_TROPA) : PUNTOS_DEFENSA) + aldea.getAumentoDefensa())
                ,NOMBRE_TROPA, NOMBRE_OBJETO, mejora);
    }
}
