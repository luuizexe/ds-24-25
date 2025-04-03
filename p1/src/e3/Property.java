package e3;

import java.util.Objects;

// Declaracion de Property
public record Property(PropertyType type, String cadastre, String address, String postalCode, int meters, int rooms, int bathrooms) {
    public enum PropertyType {APARTMENT, LOCAL}

    @Override
    public String toString() {
        return """
        %s
        %s
        %s
        %s
        %d meters, %d rooms, %d bathrooms
        """.formatted(type, cadastre, address, postalCode, meters, rooms, bathrooms);
    }

    // Son iguales si coinciden en el numero de catastro
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(cadastre, property.cadastre);
    }

    // Son iguales si el hashcode del catastro coincide
    @Override
    public int hashCode() {
        return Objects.hashCode(cadastre);
    }
}