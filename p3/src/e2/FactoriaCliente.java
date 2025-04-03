package e2;

public class FactoriaCliente {
        public Cliente createCliente(String tipo) {
            return switch (tipo.toLowerCase()) {
                case "sencillo" -> new ClienteSencillo();
                case "detallado" -> new ClienteDetallado();
                case "volumen" -> new ClienteVolumen();
                default -> new ClienteSencillo();
            };
        }
}
