package e2;

public class ClienteVolumen implements Cliente{

    @Override
    public boolean necesitaActualizar(InfoAccion nuevaInfo, InfoAccion antiguaInfo) {
        return nuevaInfo.getVolumen() != antiguaInfo.getVolumen();
    }

    @Override
    public String actualizar(InfoAccion infoAccion) {
        String salida = "Volumen: " + infoAccion.getVolumen();

        System.out.println(salida);
        return salida;
    }
}
