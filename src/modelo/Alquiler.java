package modelo;

public class Alquiler {
    private Cliente cliente;
    private Bicicletas bicicleta; 
    private int horasPactadas;

    public Alquiler(Cliente cliente, Bicicletas bicicleta, int horasPactadas) {
        this.cliente = cliente;
        this.bicicleta = bicicleta;
        this.horasPactadas = horasPactadas;
    }

    public Cliente getCliente() { return cliente; }
    public Bicicletas getBicicleta() { return bicicleta; }
    public int getHorasPactadas() { return horasPactadas; }

    @Override
    public String toString() {
        return "Cliente: " + cliente.getNombre() + " | Bici: " + bicicleta.getCodigo() + " | Horas pactadas: " + horasPactadas;
    }
}
