package Main;

import java.util.Scanner;
import modelo.*; 
import Gestion.GestionNegocio; 

public class Main {
    public static void main(String[] args) {
        // Corregido: System.in es la forma estándar y correcta
        Scanner entrada = new Scanner(System.in); 
        GestionNegocio gestor = new GestionNegocio();
        int opcion = 0;

        gestor.registrarBicicleta(new BiciUrbana("URB-01", "Trek"));
        gestor.registrarBicicleta(new BiciMontania("MON-01", "Giant")); 
        gestor.registrarBicicleta(new BiciElectrica("ELE-01", "Xiaomi"));

        System.out.println("=== SISTEMA DE ALQUILER INICIADO ===");

        while (opcion != 6) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Ver Bicicletas Disponibles");
            System.out.println("3. Realizar Alquiler");
            System.out.println("4. Devolver Bicicleta");
            System.out.println("5. Ver Ganancias del Dia");
            System.out.println("6. Salir");
            System.out.print("Elija una opcion: ");
            
            if (!entrada.hasNextInt()) {
                System.out.println("Error: Ingrese un numero valido.");
                entrada.next(); 
                continue;
            }
            opcion = entrada.nextInt();
            entrada.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Cedula: ");
                    String ced = entrada.nextLine();
                    System.out.print("Nombre: ");
                    String nom = entrada.nextLine();
                    System.out.print("Celular: ");
                    String tel = entrada.nextLine();
                    if (!ced.isEmpty() && !nom.isEmpty()) {
                        if (gestor.registrarCliente(ced, nom, tel)) {
                            System.out.println("Cliente registrado exitosamente.");
                        } else {
                            System.out.println("Error: El cliente ya existe.");
                        }
                    } else {
                        System.out.println("Error: Campos vacíos no permitidos.");
                    }
                    break;
                case 2:
                    System.out.println("\n--- INVENTARIO DISPONIBLE ---");
                    gestor.listarBicicletasDisponibles();
                    break;
                case 3:
                    System.out.print("Cedula del cliente: ");
                    String docCliente = entrada.nextLine();
                    System.out.print("Codigo de la bicicleta: ");
                    String codBici = entrada.nextLine();
                    System.out.print("Horas de alquiler (1-8): ");
                    if (entrada.hasNextInt()) {
                        int horas = entrada.nextInt();
                        if (gestor.realizarAlquiler(docCliente, codBici, horas)) {
                            System.out.println("Alquiler registrado. Bicicleta entregada al cliente.");
                        } else {
                            System.out.println("Error: Datos incorrectos, bicicleta no disponible o tiempo invalido.");
                        }
                    } else {
                        System.out.println("Error: Ingrese un numero para las horas.");
                    }
                    entrada.nextLine(); 
                    break;
                case 4:
                    System.out.print("Codigo de la bicicleta a devolver: ");
                    String codigoDev = entrada.nextLine();
                    System.out.print("Horas reales de uso: ");
                    if (entrada.hasNextInt()) {
                        int horasReales = entrada.nextInt();
                        gestor.procesarDevolucion(codigoDev, horasReales);
                    } else {
                        System.out.println("Error: Ingrese un numero valido.");
                    }
                    entrada.nextLine();
                    break;
                case 5:
                    gestor.mostrarGanancias();
                    break;
                case 6:
                    System.out.println("Cerrando sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }

        entrada.close();
    }
}
