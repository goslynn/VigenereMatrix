import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BigVigenere vigenere = new BigVigenere();


        System.out.println("Clave numérica ingresada:");
        vigenere.imprimirKey();


        String mensaje = "Hola123";
        System.out.println("\nMensaje original: " + mensaje);
        String mensajeEncriptado = vigenere.encrypt(mensaje);
        System.out.println("Mensaje encriptado: " + mensajeEncriptado);


        String mensajeDescifrado = vigenere.decrypt(mensajeEncriptado);
        System.out.println("Mensaje descifrado: " + mensajeDescifrado);


        String claveTexto = "AbCdEf";
        BigVigenere vigenereTexto = new BigVigenere(claveTexto);
        System.out.println("\nClave basada en texto '" + claveTexto + "':");
        vigenereTexto.imprimirKey();


        String mensajeEncriptadoConTexto = vigenereTexto.encrypt(mensaje);
        System.out.println("Mensaje encriptado con clave de texto: " + mensajeEncriptadoConTexto);

        String mensajeDescifradoConTexto = vigenereTexto.decrypt(mensajeEncriptadoConTexto);
        System.out.println("Mensaje descifrado con clave de texto: " + mensajeDescifradoConTexto);


        vigenere.reEncrypt();
    }

}