package e1;

import e1.Buques.Buque;
import e1.Buques.BuqueUltraligero;
import e1.Buques.TipoBuque;
import e1.Estados.*;

import java.util.ArrayList;
import java.util.List;

public class Flota {
    private List<Buque> buques;
    private List<String> movimientosCuenta;
    private int ingresos;
    private int gastos;

    public Flota() {
        buques = new ArrayList<>();
        movimientosCuenta = new ArrayList<>();
        ingresos = 0;
        gastos = 0;
    }

    public Flota(int ingresos) {
        buques = new ArrayList<>();
        movimientosCuenta = new ArrayList<>();
        this.ingresos = ingresos;
        gastos = 0;
    }

    public List<Buque> getBuques() {
        return buques;
    }

    public void setBuques(ArrayList<Buque> buques) {
        this.buques = buques;
    }

    public boolean addBuque(Buque buque) {
        if (buques.contains(buque)) {
            return false;
        }

        return buques.add(buque);
    }

    public int getFondos() {
        return ingresos - gastos;
    }

    public void comenzarEjercicioBuque(Buque buque) {
        int indice = buques.indexOf(buque);
        if (indice != -1) {
            Buque b = buques.get(indice);
            if (b.getEstado() instanceof EnBase) {
                b.getEstado().empezarEjercicio(b);
            }
        } else {
            System.out.println(buque + " no se encuentra en la lista");
        }
    }

    public void hundirBuque(Buque buque) {
        int indice = buques.indexOf(buque);
        if (indice != -1) {
            Buque b = buques.get(indice);
            if (b.getEstado() instanceof EnBatalla) {
                b.getEstado().hundir(b);
            }
        } else {
            System.out.println(buque + " no se encuentra en la lista");
        }
    }

    public void desmantelarBuque(Buque buque) {
        int indice = buques.indexOf(buque);
        if (indice != -1) {
            Buque b = buques.get(indice);
            if ((b.getEstado() instanceof EnBase) || (b.getEstado() instanceof PendienteReparacion)) {
                b.getEstado().desmantelar(b);
            }
        } else {
            System.out.println(buque + " no se encuentra en la lista");
        }
    }

    public void cobrarRecompensa(Buque buque) {
        int indice = buques.indexOf(buque);
        if (indice != -1) {
            Buque b = buques.get(indice);
            if (b.getEstado() instanceof EnBatalla) {
                b.getEstado().terminarEjercicio(b);
                ingresos += b.getRecompensa();
                movimientosCuenta.add("Ingreso de " + b.getRecompensa() + " euros por el ejercicio realizado de " + b);
            }
        } else {
            System.out.println(buque + " no se encuentra en la lista");
        }
    }

    public int getIngresos() {
        return ingresos;
    }

    public int getGastos() {
        return gastos;
    }

    public List<String> getMovimientosCuenta() {
        return movimientosCuenta;
    }

    public void pedirReparacion(Buque buque) {
        int indice = buques.indexOf(buque);
        if (indice != -1) {
            Buque b = buques.get(indice);
            if (b.getEstado() instanceof EnBase) {
                b.getEstado().solicitarReparacion(b);
            }
        } else {
            System.out.println(buque + " no se encuentra en la lista");
        }
    }

    public void cancelarReparacion(Buque buque) {
        int indice = buques.indexOf(buque);
        if (indice != -1) {
            Buque b = buques.get(indice);
            if (b.getEstado() instanceof PendienteReparacion || b.getEstado() instanceof EnReparacion) {
                b.getEstado().cancelarReparacion(b);
            }
        } else {
            System.out.println(buque + " no se encuentra en la lista");
        }
    }

    public boolean hayAlgunBuqueEnReparacion() {
        for (Buque buque : buques) {
            if (buque.getEstado() instanceof EnReparacion) {
                return true;
            }
        }
        return false;
    }

    public void confirmarReparacion(Buque buque) {
        int indice = buques.indexOf(buque);
        if (indice != -1) {
            Buque b = buques.get(indice);
            if (b.getEstado() instanceof PendienteReparacion && (getFondos() - b.getCosteReparacion()) >= 0) {
                b.getEstado().confirmarReparacion(b);
                gastos += b.getCosteReparacion();
                movimientosCuenta.add("Gasto de " + b.getCosteReparacion() + " euros por el coste de reparacion de " + b);
            }
        } else {
            System.out.println(buque + " no se encuentra en la lista");
        }
    }

    public void terminarReparacion(Buque buque) {
        int indice = buques.indexOf(buque);
        if (indice != -1) {
            Buque b = buques.get(indice);
            // Reparacion express
            if ((b instanceof BuqueUltraligero && b.getEstado() instanceof PendienteReparacion && !hayAlgunBuqueEnReparacion())
                    // Reparacion normal
                    || b.getEstado() instanceof EnReparacion) {
                b.getEstado().terminarReparacion(b);
            }
        } else {
            System.out.println(buque + " no se encuentra en la lista");
        }
    }

    public List<String> listadoActivos() {
        List<String> salida = new ArrayList<>();
        salida.add("Buques activos: \n ------------------------------------------------------------");
        for (Buque buque : buques) {
            if (!(buque.getEstado() instanceof Inactivo)) {
                salida.add("Nombre: " + buque + " | Estado: " + buque.getEstado() + " | Misiones: " + buque.getMisiones());
            }
        }
        return salida;
    }

    public List<String> estadoCuentas() {
        List<String> salida = new ArrayList<>();
        int ingresosPrevistos = 0, gastosPrevistos = 0;
        salida.add("Cuentas de la flota: \n ------------------------------------------------------------");
        salida.add("Fondos disponibles: " + getFondos());
        salida.add("Historial de movimientos: ");
        salida.addAll(movimientosCuenta);
        salida.add("Ingresos totales: " + ingresos);
        salida.add("Gastos totales: " + gastos);
        salida.add("Reparaciones pendientes:");

        for (Buque buque : buques) {

            if (buque.getEstado() instanceof PendienteReparacion) {
                salida.add(buque + " necesita una reparacion que cuesta " + buque.getCosteReparacion() + " euros.");
                gastosPrevistos += buque.getCosteReparacion();
            } else if (buque.getEstado() instanceof EnReparacion) {
                salida.add(buque + " realizando una reparacion que cuesta " + buque.getCosteReparacion() + " euros.");
            } else if(buque.getEstado() instanceof EnBatalla) {
                ingresosPrevistos += buque.getRecompensa();
            }
        }
        // Estos son ingresos y gastos de buques que todavia estan EnBatalla y PendienteReparacion (no se sumaron aun)
        salida.add("Ingresos previstos: " + ingresosPrevistos);
        salida.add("Gastos previstos: " + gastosPrevistos);

        return salida;
    }

    public List<String> listadoInactivos() {
        List<String> salida = new ArrayList<>();

        salida.add("Buques inactivos: \n ------------------------------------------------------------");
        for (Buque buque : buques) {
            if (buque.getEstado() instanceof Inactivo) {
                salida.add("Nombre: " + buque + " | Razones: " + buque.getEstado() + " | Misiones: " + buque.getMisiones());
            }
        }
        return salida;
    }


}
