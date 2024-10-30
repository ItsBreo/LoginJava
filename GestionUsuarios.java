import java.util.ArrayList;
import java.util.Scanner;

public class GestionUsuarios {
    private static ArrayList<String> usuarios = new ArrayList<>();
    private static ArrayList<String> contraseñas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            String usuario = pedirNombreUsuario();
            if (usuario.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break; // Salir del bucle
            }

            if (usuarios.contains(usuario)) {
                // Usuario registrado, pedir contraseña para iniciar sesión
                iniciarSesion(usuario);
            } else {
                // Si el usuario no existe, proceder con el registro
                System.out.println("Usuario no encontrado. Registrando nuevo usuario...");
                agregarUsuario(usuario);
            }
        }
        scanner.close(); // Cerrar el scanner
    }

    private static String pedirNombreUsuario() {
        System.out.print("Ingrese un nombre de usuario (o 'salir' para terminar): ");
        return scanner.nextLine();
    }

    private static void agregarUsuario(String usuario) {
        System.out.print("Ingrese la contraseña para el usuario '" + usuario + "': ");
        String contraseña = scanner.nextLine();

        // Agregar el nuevo usuario y su contraseña a las listas
        usuarios.add(usuario);
        contraseñas.add(contraseña);
        System.out.println("Usuario '" + usuario + "' agregado exitosamente.");
    }

    private static void iniciarSesion(String usuario) {
        int intentos = 3;
        boolean inicioExitoso = false;

        while (intentos > 0) {
            System.out.print("Ingrese la contraseña para el usuario '" + usuario + "': ");
            String contraseña = scanner.nextLine();

            // Verificar si la contraseña es correcta
            if (verificarContraseña(usuario, contraseña)) {
                System.out.println("Inicio de sesión exitoso. Bienvenido " + usuario + ".");
                inicioExitoso = true;
                // Aquí puedes añadir funciones adicionales que solo los usuarios autenticados pueden usar
                break; // Salir del bucle si el inicio de sesión es exitoso
            } else {
                intentos--;
                System.out.println("Contraseña incorrecta. Quedan " + intentos + " intento(s).");

                if (intentos == 0) {
                    System.out.println("Demasiados intentos fallidos. Pausando por 3 segundos...");
                    pausar(3000); // Pausa de 3 segundos
                    intentos = 3; // Restablecer intentos
                }
            }
        }

        // Si no logró iniciar sesión después de 3 intentos
        if (!inicioExitoso) {
            System.out.println("No se pudo iniciar sesión. Regresando a la pantalla principal.");
        }
    }

    private static boolean verificarContraseña(String usuario, String contraseña) {
        // Obtener el índice del usuario y verificar la contraseña
        int index = usuarios.indexOf(usuario);
        if (index != -1) {
            return contraseñas.get(index).equals(contraseña);
        }
        return false; // Usuario no encontrado
    }

    private static void pausar(int milisegundos) {
        try {
            Thread.sleep(milisegundos); // Pausa el programa por el tiempo especificado
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
        }
    }
}