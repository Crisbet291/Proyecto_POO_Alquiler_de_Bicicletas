package Gestion;

import java.util.ArrayList;
import modelo.*;

public class GestionNegocio {
    private ArrayList<Cliente> clientes;
    private ArrayList<Bicicletas> inventario; 
    private ArrayList<Alquiler> alquileresActivos;
    private double totalRecaudado;

    public GestionNegocio() {
        clientes = new ArrayList<>();
        inventario = new ArrayList<>();
        alquileresActivos = new ArrayList<>();
        totalRecaudado = 0.0;
    }

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
        boolean hay = false;
        for (Bicicletas b : inventario) {
            if (b.getEstado().equals("Disponible")) {
                System.out.println(b.toString());
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay bicicletas disponibles en este momento.");
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
            Alquiler nuevoAlquiler = new Alquiler(c, b, horas);
            b.setEstado("Ocupada"); 
            alquileresActivos.add(nuevoAlquiler);
            return true;
        }
        return false;
    }

    public void procesarDevolucion(String codigoBici, int horasReales) {
        Alquiler alquilerTerminado = null;
        
        for (Alquiler a : alquileresActivos) {
            if (a.getBicicleta().getCodigo().equals(codigoBici)) {
                alquilerTerminado = a;
                break;
            }
        }

        if (alquilerTerminado != null) {
            Bicicletas b = alquilerTerminado.getBicicleta();
            double costoBase = 0.0;
            int horasPactadas = alquilerTerminado.getHorasPactadas();

            if (horasPactadas >= 3) {
                costoBase = b.calcularCosto(horasPactadas, 0.10);
            } else {
                costoBase = b.calcularCosto(horasPactadas);
            }

            double recargo = 0.0;
            if (horasReales > horasPactadas) {
                int horasExcedentes = horasReales - horasPactadas;
                recargo = horasExcedentes * (b.getTarifaBase() * 1.5);
            }

            double totalPagar = costoBase + recargo;
            totalRecaudado += totalPagar;
            b.setEstado("Disponible");
            alquileresActivos.remove(alquilerTerminado);

            System.out.println("--- FACTURA DE DEVOLUCION ---");
            System.out.println("Cliente: " + alquilerTerminado.getCliente().getNombre());
            System.out.println("Bicicleta: " + b.getCodigo());
            System.out.println("Costo base (con/sin descuento): $" + costoBase);
            if (recargo > 0) {
                System.out.println("Recargo por " + (horasReales - horasPactadas) + " horas de retraso: $" + recargo);
            }
            System.out.println("TOTAL A PAGAR: $" + totalPagar);
            System.out.println("-----------------------------");
        } else {
            System.out.println("Error: No se encontro un alquiler activo con esa bicicleta.");
        }
    }

    public void mostrarGanancias() {
        System.out.println("Ganancia total del dia: $" + totalRecaudado);
    }
}