package modelo;

public interface Alquilable {
    double calcularCosto(int horas);
    double calcularCosto(int horas, double descuento);
}
