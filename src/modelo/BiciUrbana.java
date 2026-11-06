package modelo;

public class BiciUrbana extends Bicicletas {
    public BiciUrbana(String codigo, String marca) {
        super(codigo, marca, 2.50); 
    }

    @Override
    public double calcularCosto(int horas) {
        return getTarifaBase() * horas;
    }

    @Override
    public double calcularCosto(int horas, double descuento) {
        return calcularCosto(horas) - (calcularCosto(horas) * descuento);
    }
    
    @Override
    public String toString() { return "[Urbana] " + super.toString(); }
}
