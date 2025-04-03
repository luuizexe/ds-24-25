package e2;

import java.util.ArrayList;
import java.util.List;

public class Accion extends Sujeto {

    private InfoAccion infoAccion;

    public Accion() {
        clientes = new ArrayList<Cliente>();
    }

    public Accion(InfoAccion infoAccion) {
        clientes = new ArrayList<Cliente>();
        this.infoAccion = infoAccion;
    }

    public List<String> setInfoAccion(InfoAccion nuevaInfo) {
        if (nuevaInfo == null) {
            throw new IllegalArgumentException("La nueva informacion no debe ser nula");
        }

        if (infoAccion == null || !infoAccion.esMismaInfo(nuevaInfo)) {
            return notificar(nuevaInfo);
        }
        return new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    @Override
    void insertarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    @Override
    List<String> notificar(InfoAccion nuevaInfo) {
        List<String> salida = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.necesitaActualizar(infoAccion, nuevaInfo)) {
                salida.add(cliente.actualizar(nuevaInfo));
            }
        }

        infoAccion = nuevaInfo;
        return salida;
    }

}