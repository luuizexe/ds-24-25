package e1.Aldeas;

public class Tropa {
    private Aldea aldea;
    private int puntosAtaque;
    private int puntosDefensa;
    private boolean mejora;
    private String tipoTropa;
    private String nombreObjeto;

    public Tropa(Aldea aldea, int puntosAtaque, int puntosDefensa, String tipoTropa, String nombreObjeto, boolean mejora) {
        this.aldea = aldea;
        this.puntosAtaque = puntosAtaque;
        this.puntosDefensa = puntosDefensa;
        this.mejora = mejora;
        this.tipoTropa = tipoTropa;
        this.nombreObjeto = nombreObjeto;
    }

    public Tropa(Aldea aldea, int puntosAtaque, int puntosDefensa, String tipoTropa, boolean mejora) {
        this.aldea = aldea;
        this.puntosAtaque = puntosAtaque;
        this.puntosDefensa = puntosDefensa;
        this.mejora = mejora;
        this.tipoTropa = tipoTropa;
    }

    public Aldea getAldea() {
        return aldea;
    }

    public void setAldea(Aldea aldea) {
        this.aldea = aldea;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

    public void setPuntosAtaque(int puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
    }

    public int getPuntosDefensa() {
        return puntosDefensa;
    }

    public void setPuntosDefensa(int puntosDefensa) {
        this.puntosDefensa = puntosDefensa;
    }

    public boolean isMejora() {
        return mejora;
    }

    public void setMejora(boolean mejora) {
        this.mejora = mejora;
    }

    public String getTipoTropa() {
        return tipoTropa;
    }

    public void setTipoTropa(String tipoTropa) {
        this.tipoTropa = tipoTropa;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    @Override
    public String toString() {
        if(!mejora) {
            return aldea.getTipoAldea() + " Soldiery - " + tipoTropa;
        }
        return aldea.getTipoAldea() + " Soldiery - " + tipoTropa + " with " + nombreObjeto;
    }
}
