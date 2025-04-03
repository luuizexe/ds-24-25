package e1.Aldeas.Romanos;

import e1.Aldeas.Tropa;

public class TropaEquitesImperatoris extends Tropa
{
    private static final int PUNTOS_ATAQUE = 120;
    private static final int PUNTOS_DEFENSA = 65;
    private static final String NOMBRE_TROPA = "Equites Imperatoris";

    public TropaEquitesImperatoris(AldeaRomanos aldea) {
        super(aldea, (int) (PUNTOS_ATAQUE * aldea.getAumentoAtaque()),
                (int) (PUNTOS_DEFENSA + aldea.getAumentoDefensa()),NOMBRE_TROPA, false);
    }
}
