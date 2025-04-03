package e2;

public class ClienteSencillo implements Cliente{

    @Override
    public boolean necesitaActualizar(InfoAccion nuevaInfo, InfoAccion antiguaInfo) {
        return nuevaInfo.getCierre() != antiguaInfo.getCierre();
    }

    @Override
    public String actualizar(InfoAccion infoAccion) {
        String salida = "Cierre: " + infoAccion.getCierre();

        System.out.println(salida);
        return salida;
    }
}
