package e1;

import e1.Buques.*;

public class FactoriaBuque {


    public Buque createBuque(String nombre, TipoBuque tipoBuque) {
        return switch (tipoBuque) {
            case DE, DD -> new BuqueUltraligero(nombre, tipoBuque);
            case CL, AV -> new BuqueLigero(nombre, tipoBuque);
            case CA, CV -> new BuquePesado(nombre, tipoBuque);
            case BB -> new BuqueUltrapesado(nombre, tipoBuque);
            // Por si se introducen nuevos elementos en el switch, ponemos un  caso default para que no se rompa el codigo
            default -> new BuqueUltraligero(nombre, tipoBuque);
        };
    }
}
