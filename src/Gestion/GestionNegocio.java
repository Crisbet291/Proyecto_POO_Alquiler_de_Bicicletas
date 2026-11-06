package Gestion;

import java.util.ArrayList;
import modelo.*;

public class GestionNegocio {

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Bicicletas> inventario = new ArrayList<>();
    private ArrayList<Alquiler> alquileresActivos = new ArrayList<>();
    private double totalRecaudado = 0;

    public boolean registrarCliente(String documento, String nombre, String telefono) {
        for (Cliente c : clientes) {
            if (c.getDocumento().equals(documento)) {
                return false;
            }
        }
        clientes.add(new Cliente(documento, nombre, telefono));
        return true;
    }

    public boolean registrarBicicleta(Bicicletas bici) {
        for (Bicicletas b : inventario) {
            if (b.getCodigo().equals(bici.getCodigo())) {
                return false;
            }
        }
        inventario.add(bici);
        return true;
    }

    public void listarBicicletasDisponibles() {
        for (Bicicletas b : inventario) {
            if (b.getEstado().equals("Disponible")) {
                System.out.println(b);
            }
        }
    }

    public Cliente buscarCliente(String documento) {
        for (Cliente c : clientes) {
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        return null;
    }

    public Bicicletas buscarBicicletaDisponible(String codigo) {
        for (Bicicletas b : inventario) {
            if (b.getCodigo().equals(codigo) && b.getEstado().equals("Disponible")) {
                return b;
            }
        }
        return null;
    }

    public boolean realizarAlquiler(String documento, String codigoBici, int horas) {
        Cliente c = buscarCliente(documento);
        Bicicletas b = buscarBicicletaDisponible(codigoBici);

        if (c != null && b != null && horas >= 1 && horas <= 8) {
            alquileresActivos.add(new Alquiler(c, b, horas));
            b.setEstado("Ocupada");
            return true;
        }
        return false;
    }

    public void procesarDevolucion(String codigoBici, int horasReales) {
        Alquiler alquiler = null;

        for (Alquiler a : alquileresActivos) {
            if (a.getBicicleta().getCodigo().equals(codigoBici)) {
                alquiler = a;
                break;
            }
        }

        if (alquiler == null) {
            System.out.println("No existe un alquiler activo con esa bicicleta.");
            return;
        }

        Bicicletas b = alquiler.getBicicleta();
        int horas = alquiler.getHorasPactadas();

        double costo;
        if (horas >= 3) {
            costo = b.calcularCosto(horas, 0.10);
        } else {
            costo = b.calcularCosto(horas);
        }

        double recargo = 0;
        if (horasReales > horas) {
            recargo = (horasReales - horas) * (b.getTarifaBase() * 1.5);
        }

        double total = costo + recargo;
        totalRecaudado += total;

        b.setEstado("Disponible");
        alquileresActivos.remove(alquiler);

        System.out.println("--- FACTURA ---");
        System.out.println("Cliente: " + alquiler.getCliente().getNombre());
        System.out.println("Bicicleta: " + b.getCodigo());
        System.out.println("Costo base: $" + costo);
        System.out.println("Recargo: $" + recargo);
        System.out.println("TOTAL: $" + total);
    }

    public void mostrarGanancias() {
        System.out.println("Ganancia total del dia: $" + totalRecaudado);
    }
}
