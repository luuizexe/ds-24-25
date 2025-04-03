package e3;

import java.util.Objects;

public class Ad {
    // Declaracion de datos de Ad
    private String agency;
    private Property property;

    public enum AdType {PURCHASE, RENTAL}

    private AdType type;

    private int price;

    // Constructor pasando los datos por separado
    public Ad(String agency, Property property, AdType type, int price) {
        this.property = property;
        this.price = price;
        this.type = type;
        this.agency = agency;
    }

    // Constructor pasando un objeto ya creado
    public Ad(Ad ad) {
        this.property = ad.property;
        this.price = ad.price;
        this.type = ad.type;
        this.agency = ad.agency;
    }

    // Comparamos si dos anuncios tienen la misma propiedad
    public boolean isPropertyEqual(Ad ad2) {
        return property.equals(ad2.property);
    }

    // Indicamos si un anuncio tiene un precio normal
    public boolean isPriceNormal() {
        if (type == AdType.PURCHASE) {
            return price > 0;
        } else {
            return (price > 0 && price <= 5000);
        }
    }

    public float priceMetersEuros() {
        // Si los metros o el precio son 0, devolvemos un error
        if (price == 0 || property.meters() == 0) throw new ArithmeticException();
            // Si no, devolvemos el precio por metro cuadrado
        else return (float) price / property.meters();
    }

    public void dropPrice(int discount) {
        // Si el descuento no es valido, devolvemos un error
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException();
        } else
            // Si no, descontamos por porcentaje
            price -= (int) (price * ((float) discount / 100));
    }

    // Devolvemos el precio
    public int getPriceInEuros() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return price == ad.price && Objects.equals(agency, ad.agency) && Objects.equals(property, ad.property) && type == ad.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(agency, property, type, price);
    }
}