package e1;

import e1.Aldeas.Aldea;

import java.util.ArrayList;
import java.util.List;

public class Travian {
    public static List<String> batalla(Aldea aldeaA, Aldea aldeaB) {
        List<String> salida = new ArrayList<>();

        salida.add("### Starts the battle! --> " + aldeaA.getNombre() + " Attacks " + aldeaB.getNombre() + "! ###");
        salida.add(aldeaA.toString());
        salida.add("Total " + aldeaA.getNombre() + " attack power: " + aldeaA.getPuntosAtaqueTotales() + "\n");
        salida.add(aldeaB.toString());
        salida.add("Total " + aldeaB.getNombre() + " defense power: " + aldeaB.getPuntosDefensaTotales());
        if (aldeaA.getPuntosAtaqueTotales() > aldeaB.getPuntosDefensaTotales()) {
            salida.add("\n" + aldeaA.getNombre() + " with an age of " + aldeaA.getAnhos() + " years WINS!");
        } else {
            salida.add("\n" + aldeaB.getNombre() + " with an age of " + aldeaB.getAnhos() + " years WINS!");
        }
        return salida;
    }
}
