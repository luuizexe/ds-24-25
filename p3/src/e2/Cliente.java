package e2;

public interface Cliente {
    boolean necesitaActualizar(InfoAccion nuevaInfo, InfoAccion antiguaInfo);
    String actualizar(InfoAccion infoAccion);
}
