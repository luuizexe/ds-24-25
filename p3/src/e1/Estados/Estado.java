package e1.Estados;

import e1.Buques.*;

public interface Estado {
        void empezarEjercicio(Buque buque);
        void terminarEjercicio(Buque buque);
        void solicitarReparacion(Buque buque);
        void confirmarReparacion(Buque buque);
        void terminarReparacion(Buque buque);
        void cancelarReparacion(Buque buque);
        void desmantelar(Buque buque);
        void hundir(Buque buque);
}
