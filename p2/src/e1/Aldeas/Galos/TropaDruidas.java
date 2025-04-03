package e1.Aldeas.Galos;

import e1.Aldeas.Tropa;

public class TropaDruidas extends Tropa {
    private static final int PUNTOS_ATAQUE = 45;
    private static final int PUNTOS_DEFENSA = 115;
    private static final String NOMBRE_TROPA = "Druidrider";

    public TropaDruidas(AldeaGalos aldea) {
        super(aldea, (int) (PUNTOS_ATAQUE * aldea.getAumentoAtaque()),
                (int) (PUNTOS_DEFENSA + aldea.getAumentoDefensa()), NOMBRE_TROPA,  false);
    }
}
