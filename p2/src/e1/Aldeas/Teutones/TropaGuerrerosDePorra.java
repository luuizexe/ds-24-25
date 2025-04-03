package e1.Aldeas.Teutones;

import e1.Aldeas.Tropa;

public class TropaGuerrerosDePorra extends Tropa {
    private static final int PUNTOS_ATAQUE = 40;
    private static final int PUNTOS_DEFENSA = 20;
    private static final double AUMENTO_ATAQUE_TROPA = 1.25;
    private static final String NOMBRE_TROPA = "Mazeman";
    private static final String NOMBRE_OBJETO = "iron maze";

    public TropaGuerrerosDePorra(AldeaTeutones aldea, boolean mejora) {
        super(aldea, (int) ((mejora ? (PUNTOS_ATAQUE * AUMENTO_ATAQUE_TROPA) : PUNTOS_ATAQUE) * aldea.getAumentoAtaque()),
                (int) (PUNTOS_DEFENSA + aldea.getAumentoDefensa()), NOMBRE_TROPA, NOMBRE_OBJETO, mejora);
    }
}
