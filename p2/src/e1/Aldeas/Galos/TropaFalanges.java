package e1.Aldeas.Galos;

import e1.Aldeas.Tropa;

public class TropaFalanges extends Tropa {
    private static final int PUNTOS_ATAQUE = 15;
    private static final int PUNTOS_DEFENSA = 40;
    private static final String NOMBRE_TROPA = "Phalanx";

    public TropaFalanges(AldeaGalos aldea) {
        super(aldea, (int) (PUNTOS_ATAQUE * aldea.getAumentoAtaque()),
                (int) (PUNTOS_DEFENSA + aldea.getAumentoDefensa()), NOMBRE_TROPA,false);
    }
}
