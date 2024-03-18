import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String dictionary = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.,\"\":-!? ";
    private static String archivoNormal = "textoNormal.txt";
    private static String archivoCifrado = "textoCifrado.txt";
    private static String archivoDescifrado = "textoDescifrado.txt";

    public static void main(String[] args) throws IOException {
        showMenu();
    }

    public static void showMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option;
        int exit = 0;
        do {

            System.out.println("------------- Bienvenido ------------");
            System.out.println("");
            System.out.println("Escoge una de las siguientes opciones");
            System.out.println("1: Cifrado/Descifrado");
            System.out.println("2: Criptoanalisis fuerza bruta");
            System.out.println("0. Exit");

            option = scanner.nextInt();

            switch (option) {
                case 0:
                    exit = 0;
                    break;
                case 1:
                    showMenuCesar();
                    break;
                case 2:
                        fuerzaBruta(archivoCifrado);
                    break;
                default:
                    System.out.println();
                    System.out.println("....¡¡Selecciona una opción!!....");
                    System.out.println();
                    exit = 1;
                    break;
            }


        } while (exit != 0);
    }

    private static void showMenuCesar() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option;
        int exit = 1;

        do {
            System.out.println();
            System.out.println("--------- Cesar --------");
            System.out.println();


            System.out.println("1: Cifrar");
            System.out.println("2: Descifrar");
            System.out.println("0. Regresar al Menu");
            System.out.println();
            option = scanner.nextInt();
            switch (option) {
                case 0:
                    exit = 0;
                    showMenu();
                    break;
                case 1:
//                    cifrarArchivo(archivoNormal, archivoCifrado, clave);
                    showMenuCifrar();
                    break;
                case 2:
//                    descifrarArchivo(archivoCifrado, archivoDescifrado, clave);
                    showMenuDescifrar();
                    break;
                default:
                    System.out.println();
                    System.out.println("....¡¡Selecciona una opción!!....");
                    System.out.println();
                    exit = 1;
                    break;
            }


        }while(exit !=0);
    }

    private static void showMenuCifrar() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option;
        int exit = 1;

        do {
            System.out.println();
            System.out.println("--------- Crifando Menasje --------");
            System.out.println();


            System.out.println("1: Ingresar el valor clave para cifrar el mensaje");
            System.out.println("0. Regresar al Menu");
            System.out.println();
            option = scanner.nextInt();
            switch (option) {
                case 0:
                    exit = 0;
                    showMenu();
                    break;
                case 1:
                    System.out.println("Ingrese el valor clave");
                    int clave = scanner.nextInt();
                    cifrarArchivo(archivoNormal, archivoCifrado, clave);
                    break;
                default:
                    System.out.println();
                    System.out.println("....¡¡Selecciona una opción!!....");
                    System.out.println();
                    exit = 1;
                    break;
            }


        }while(exit !=0);
    }


    private static void showMenuDescifrar() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option;
        int exit = 1;

        do {
            System.out.println();
            System.out.println("--------- Descrifando Menasje --------");
            System.out.println();


            System.out.println("1: Ingresar el valor clave para descifrar el mensaje");
            System.out.println("0. Regresar al Menu");
            System.out.println();
            option = scanner.nextInt();
            switch (option) {
                case 0:
                    exit = 0;
                    showMenu();
                    break;
                case 1:
                    System.out.println("Ingrese el valor clave");
                    int clave = scanner.nextInt();
                    descifrarArchivo(archivoCifrado, archivoDescifrado, clave);
                    break;
                default:
                    System.out.println();
                    System.out.println("....¡¡Selecciona una opción!!....");
                    System.out.println();
                    exit = 1;
                    break;
            }


        }while(exit !=0);
    }


    private static void cifrarArchivo(String archivoEntrada, String archivoSalida, int clave) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {

            int character;
            while ((character = reader.read()) != -1) {
                char originalChar = (char) character;
                char encryptedChar = cifrarCaracter(originalChar, clave);
                writer.write(encryptedChar);
            }
        }
    }

    private static char cifrarCaracter(char caracter, int clave) {
        int index = dictionary.indexOf(caracter);
        if (index == -1) {
            return caracter; // Mantener caracteres no cifrables tal como están
        }
        int newIndex = (index + clave + dictionary.length()) % dictionary.length();
        return dictionary.charAt(newIndex);
    }
    private static void descifrarArchivo(String archivoEntrada, String archivoSalida, int clave) throws IOException {
        cifrarArchivo(archivoEntrada, archivoSalida, -clave);
    }

    private static void fuerzaBruta(String archivoEntrada) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoEntrada))) {
            String textoCifrado = reader.readLine();
            for (int clave = 1; clave < dictionary.length(); clave++) {
                String posibleTexto = descifrarTexto(textoCifrado, clave);
                System.out.println("Intento con clave " + clave + ": " + posibleTexto);
            }
        }
    }
    private static String descifrarTexto(String textoCifrado, int clave) {
        StringBuilder builder = new StringBuilder();
        for (char c : textoCifrado.toCharArray()) {
            builder.append(cifrarCaracter(c, -clave));
        }
        return builder.toString();
    }
}