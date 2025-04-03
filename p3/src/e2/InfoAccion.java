package e2;

public class InfoAccion {
    private String simbolo;
    private double cierre;
    private double maximo;
    private double minimo;
    private int volumen;

    public InfoAccion(String simbolo, double cierre, double maximo, double minimo, int volumen) {
        if (simbolo == null || simbolo.isBlank()) {
            throw new IllegalArgumentException("El simbolo debe contener algo");
        }

        if(simbolo.length() > 4) {
           simbolo = simbolo.substring(0, 4);
        }
        simbolo = simbolo.toUpperCase();

        if(cierre < 0 || maximo < 0 || minimo < 0 || volumen <0) {
            throw new IllegalArgumentException("La accion no debe tener ningun parametro negativo");
        }

        if(!(minimo <= cierre && maximo >= cierre)) {
            throw new IllegalArgumentException("Datos incorrectos, debe ser del estilo: minimo <= cierre <= maximo");
        }

        this.simbolo = simbolo;
        this.cierre = cierre;
        this.maximo = maximo;
        this.minimo = minimo;
        this.volumen = volumen;
    }

    public boolean esMismaInfo(InfoAccion info) {

        if (info == null) return false;
        // Por ahora comparamos todos, pero igual en un futuro obviamos por ejemplo el simbolo, ya que deberia ser igual siempre
        return this.simbolo.equals(info.simbolo) &&
                this.cierre == info.cierre &&
                this.maximo == info.maximo &&
                this.minimo == info.minimo &&
                this.volumen == info.volumen;
    }
    public String getSimbolo() {
        return simbolo;
    }

    public int getVolumen() {
        return volumen;
    }

    public double getMinimo() {
        return minimo;
    }

    public double getMaximo() {
        return maximo;
    }

    public double getCierre() {
        return cierre;
    }
}
