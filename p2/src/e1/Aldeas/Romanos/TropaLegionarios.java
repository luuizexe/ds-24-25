package e1.Aldeas.Romanos;

import e1.Aldeas.Tropa;

public class TropaLegionarios extends Tropa {
    private static final int PUNTOS_ATAQUE = 40;
    private static final int PUNTOS_DEFENSA = 35;
    private static final String NOMBRE_TROPA = "Legionary";

    public TropaLegionarios(AldeaRomanos aldea) {
        super(aldea, (int) (PUNTOS_ATAQUE * aldea.getAumentoAtaque()),
                (int) (PUNTOS_DEFENSA + aldea.getAumentoDefensa()), NOMBRE_TROPA, false);
    }
}
