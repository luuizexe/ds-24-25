package e2;

import java.util.List;

public abstract class Sujeto {
    protected List<Cliente> clientes;
    abstract void insertarCliente(Cliente cliente);
    abstract void eliminarCliente(Cliente cliente);
    abstract List<String> notificar(InfoAccion nuevaInfo);
}
