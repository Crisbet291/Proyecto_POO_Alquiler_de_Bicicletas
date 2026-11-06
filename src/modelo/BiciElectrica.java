package modelo;

public class BiciElectrica extends Bicicletas {
    private double cargoBateria;

    public BiciElectrica(String codigo, String marca) {
        super(codigo, marca, 6.00); 
        this.cargoBateria = 2.00; 
    }

    @Override
    public double calcularCosto(int horas) {
        return (getTarifaBase() * horas) + cargoBateria;
    }

    @Override
    public double calcularCosto(int horas, double descuento) {
        double subtotal = getTarifaBase() * horas;
        double subtotalConDescuento = subtotal - (subtotal * descuento);
        return subtotalConDescuento + cargoBateria; 
    }
    
    @Override
    public String toString() { return "[Electrica] " + super.toString() + " (+ $" + cargoBateria + " bateria)"; }
}
