package com.example;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ActividadesDeProgramacion3_jomartinez {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int menu = -1;
        while (menu != 0) {
            System.out.println("\n");
            System.out.println("Menu de opciones");
            System.out.println("0. Salir del menu");
            System.out.println("1. Division de dos numeros enteros (try-catch)");
            System.out.println("2. Control de datos introducidos por teclado");
            System.out.println("3. Lanzamiento manual de excepciones (throw)");
            System.out.println("4. Declaración de excepciones (throws)");
            System.out.println("5. Excepciones personalizadas");
            System.out.println("6. Sistema de gestión de notas");
            System.out.println("7. Ejercicio final: Sistema de reservas de hotel");
            System.out.print("Introduzca el numero de la opcion: ");
            menu = scanner.nextInt();
            System.out.println();

            switch (menu) {
                case 1:
                    division();
                    break;
                case 2:
                    controlDatos();
                    break;
                case 3:
                    try {
                        lanzamientoManualExcepciones();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: La edad debe ser igual o superior a 18 años.");
                    }
                    break;
                case 4:
                    try {
                        declaracionExcepciones();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: No se puede calcular la raíz cuadrada de un número negativo.");
                    }
                    break;
                case 5:
                    System.out.print("Titular: ");
                    String titular = scanner.next();
                    System.out.print("Saldo inicial: ");
                    double saldo = scanner.nextDouble();
                    try {
                        excepcionesPersonalizadas(titular, saldo);
                    } catch (SaldoInsuficienteException e) {
                        System.out.println("Error: Saldo insuficiente para realizar la operación.");
                        System.out.println("Saldo actual: " + saldo);
                    }
                    break;
                case 6:
                    while (true) {
                        try {
                            System.out.print("Introduce una nota: ");
                            double nota = scanner.nextDouble();
                            validarNota(nota);
                            System.out.println("Nota introducida correctamente: " + nota);
                            break;
                        } catch (Exception e) {
                            System.out.println("Error: La nota debe estar comprendida entre 0 y 10.");
                        }
                    }
                    break;
                case 7:
                    try {
                        System.out.println("===== SISTEMA DE RESERVAS DE HOTEL =====\n");
                        System.out.print("Nombre del cliente: ");
                        scanner.nextLine();
                        String nombreCliente = scanner.nextLine();
                        System.out.print("Numero de habitación: ");
                        int numHabitacion = scanner.nextInt();
                        System.out.print("Numero de noches: ");
                        int numNoches = scanner.nextInt();
                        System.out.print("Precio por noche: ");
                        double precioNoche = scanner.nextDouble();
                        validarReserva(nombreCliente, numHabitacion, numNoches, precioNoche);
                        System.out.println("\nRESERVA REALIZADA CORRECTAMENTE\n");
                        System.out.println("Cliente: " + nombreCliente);
                        System.out.println("Habitacion: " + numHabitacion);
                        System.out.println("Noches: " + numNoches);
                        System.out.println("Precio por noche: " + precioNoche);
                        System.out.println("Precio total: " + (numNoches * precioNoche));
                    } catch (Exception e) {
                        System.out.println("\n" + e);
                    }
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }

    public static void division() {
        System.out.print("Introduce el primer número: ");
        int num1 = scanner.nextInt();
        System.out.print("Introduce el segundo número: ");
        int num2 = scanner.nextInt();
        try {
            System.out.println("Resultado: " + (num1 / num2));
        } catch (ArithmeticException e) {
            System.out.println("Error: No se puede dividir entre cero.");
        }
    }

    public static void controlDatos() {
        System.out.print("Introduce tu edad: ");
        try {
            int edad = scanner.nextInt();
            System.out.println("Edad introducida: " + edad);
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes introducir un número entero.");
            scanner.next();
        }
    }

    public static void lanzamientoManualExcepciones() throws IllegalArgumentException {
        System.out.print("Introduce tu edad: ");
        int edad = scanner.nextInt();
        if (edad < 18) {
            throw new IllegalArgumentException();
        }
        System.out.println("Acceso permitido.");
    }

    public static void declaracionExcepciones() throws IllegalArgumentException {
        System.out.print("Introduce un numero: ");
        int num = scanner.nextInt();
        if (num < 0) {
            throw new IllegalArgumentException();
        }
        System.out.println("La raíz cuadrada es: " + Math.sqrt(num));
    }

    public static void excepcionesPersonalizadas(String titular, double saldo) throws SaldoInsuficienteException {
        
        System.out.print("Cantidad que desea retirar: ");
        double retirar = scanner.nextDouble();
        if (saldo < retirar) {
            throw new SaldoInsuficienteException("");
        }
        saldo -= retirar;
        System.out.println("Retirada realizada correctamente.");
        System.out.println("Saldo actual: " + saldo);
    }

    public static class SaldoInsuficienteException extends Exception {
        SaldoInsuficienteException(String mensaje) {
            super(mensaje);
        }
    }

    public static void validarNota(double nota) throws IllegalArgumentException {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException();
        }
    }

    public static void validarReserva(String nombreCliente, int numHabitacion, int numNoches, double precioNoche) throws Exception {
        if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
            throw new Exception("ERROR: El nombre del cliente no puede estar vacío.");
        }
        if (numHabitacion <= 0) {
            throw new Exception("ERROR: El numero de habitación debe ser mayor que cero.");
        }
        if (numNoches <= 0) {
            throw new Exception("ERROR: El numero de noches debe ser mayor que cero.");
        }
        if (precioNoche <= 0) {
            throw new Exception("ERROR: El precio por noche debe ser superior a cero.");
        }
    }
}
