package modelo;

public abstract class Bicicletas implements Alquilable {
    private String codigo;
    private String marca;
    private String estado;
    private double tarifaBase;

    public Bicicletas(String codigo, String marca, double tarifaBase) {
        this.codigo = codigo;
        this.marca = marca;
        this.estado = "Disponible";
        this.tarifaBase = tarifaBase;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public boolean estaDisponible() {
        return estado.equals("Disponible");
    }

    public boolean ocupar() {
        if (estaDisponible()) {
            estado = "Ocupada";
            return true;
        }
        return false;
    }
    public void liberar() {
        if (estado.equals("Ocupada")) {
            estado = "Disponible";
        }
    }

    public void marcarFueraDeServicio() {
        estado = "Fuera de servicio";
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + " | Marca: " + marca + " | Estado: " + estado + " | Tarifa: $" + tarifaBase + "/h";
    }
}
