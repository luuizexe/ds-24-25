package e1.Aldeas;

import java.util.ArrayList;
import java.util.List;

public abstract class Aldea {
    private String nombre;
    private final String tipoAldea;
    private int anhos;
    private int resistencia;
    private double aumentoAtaque;
    private double aumentoDefensa;
    private List<Tropa> tropas;
    private int puntosAtaqueTotales;
    private int puntosDefensaTotales;

    public Aldea(String nombre, String tipoAldea, int anhos, int resistencia) {
        if (anhos < 0) {
            throw new IllegalArgumentException("Los años no pueden ser negativos");
        }
        if (resistencia < 0) {
            throw new IllegalArgumentException("La resistencia no puede ser negativa");
        }

        this.nombre = nombre;
        this.tipoAldea = tipoAldea;
        this.anhos = anhos;
        this.resistencia = resistencia;
        this.tropas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoAldea() {
        return tipoAldea;
    }

    public int getAnhos() {
        return anhos;
    }

    public void setAnhos(int anhos) {
        if (anhos < 0) {
            throw new IllegalArgumentException("Los años no pueden ser negativos");
        }
        this.anhos = anhos;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        if (resistencia < 0) {
            throw new IllegalArgumentException("La resistencia no puede ser negativa");
        }
        this.resistencia = resistencia;
    }

    public double getAumentoAtaque() {
        return aumentoAtaque;
    }

    public void setAumentoAtaque(double aumentoAtaque) {
        this.aumentoAtaque = aumentoAtaque;
    }

    public double getAumentoDefensa() {
        return resistencia * aumentoDefensa;
    }

    public void setAumentoDefensa(double aumentoDefensa) {
        this.aumentoDefensa = aumentoDefensa;
    }

    // Metemos la tropa en la lista de la aldea, devolviendo true si se ha insertado correctamente
    public boolean addTropa(Tropa tropa) {
        if (tropa == null)
            return false;

        if (tropa.getAldea().getClass().equals(getClass())) {
            //Sumamos a los puntos de ataque y defensa de la aldea los de la tropa
            puntosAtaqueTotales += tropa.getPuntosAtaque();
            puntosDefensaTotales += tropa.getPuntosDefensa();
            return tropas.add(tropa);
        }
        return false;
    }
    // Sacamos la tropa en la lista de la aldea, devolviendo true si se ha insertado correctamente
    public boolean removeTropa(Tropa tropa) {
        if (tropa == null)
            return false;

        if (tropas.remove(tropa)) {
            //Restamos a los puntos de ataque y defensa de la aldea los de la tropa
            puntosAtaqueTotales -= tropa.getPuntosAtaque();
            puntosDefensaTotales -= tropa.getPuntosDefensa();
            return true;
        }

        return false;
    }

    public int getPuntosAtaqueTotales() {
        return puntosAtaqueTotales;
    }

    public int getPuntosDefensaTotales() {
        return puntosDefensaTotales;
    }

    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder();

        salida.append(nombre).append(" have the following soldiery:\n");
        for (int i = 0; i < tropas.size(); i++) {
            salida.append(tropas.get(i));
            if (i != tropas.size() - 1) {
                salida.append("\n");
            }
        }
        return salida.toString();
    }
}

