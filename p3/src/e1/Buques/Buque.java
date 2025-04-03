package e1.Buques;

import e1.Estados.*;

public abstract class Buque {
    private String nombre;
    private TipoBuque tipoBuque;
    private int misiones;
    private Estado estado;
    private int recompensa;
    private int costeReparacion;
    private boolean deteriorado;


    public Buque(String nombre, TipoBuque tipoBuque, int recompensa, int costeReparacion) {
        this.nombre = nombre;
        this.tipoBuque = tipoBuque;
        this.misiones = 0;
        this.estado = EnBase.getInstancia();
        this.recompensa = recompensa;
        this.costeReparacion = costeReparacion;
        deteriorado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getMisiones() {
        return misiones;
    }

    public void setMisiones(int misiones) {
        this.misiones = misiones;
    }

    public void misionCompletada() {
        this.misiones += 1;
    }

    public TipoBuque getTipo() {
        return tipoBuque;
    }

    public void setTipo(TipoBuque tipoBuque) {
        this.tipoBuque = tipoBuque;
    }

    public int getRecompensa() {
        return recompensa;
    }

    public int getCosteReparacion() {
        return costeReparacion;
    }

    public boolean isDeteriorado() {
        return deteriorado;
    }

    public void setDeteriorado(boolean deteriorado) {
        this.deteriorado = deteriorado;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipoBuque.getNombre() + ")";
    }
}
