package modelo;

public class BiciMontania extends Bicicletas {
    public BiciMontania(String codigo, String marca) {
        super(codigo, marca, 4.00); 
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
    public String toString() { return "[Montania] " + super.toString(); }
}
