package e2;

public class ClienteDetallado implements Cliente {
    @Override
    public boolean necesitaActualizar(InfoAccion nuevaInfo, InfoAccion antiguaInfo) {
        return !nuevaInfo.esMismaInfo(antiguaInfo);
    }

    @Override
    public String actualizar(InfoAccion infoAccion) {
        String salida = "Simbolo: " + infoAccion.getSimbolo() + "\n" +
                "Cierre: " + infoAccion.getCierre() + "\n" +
                "Maximo: " + infoAccion.getMaximo() + "\n" +
                "Minimo: " + infoAccion.getMinimo() + "\n" +
                "Volumen: " + infoAccion.getVolumen();

        System.out.println(salida);
        return salida;
    }
}
