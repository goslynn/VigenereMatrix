import java.util.Scanner;

public class BigVigenere {
    private int[] key;
    private final char[][] vigenereMatrix;
    private static final Scanner lector = new Scanner(System.in);
    private static final String CARACTERES = "abcdefghijklmnnopqrstuvwxyzABCDEFGHIJKLMNnOPQRSTUVWXYZ0123456789";

    public BigVigenere() {
        System.out.print("Ingrese el tamano de la clave numérica: ");
        int size = lector.nextInt();

        this.key = new int[size];
        int acumulador = 0;

        while (acumulador < size) {
            try {
                int verificar = pedirClave();
                if (verificar < 64) {
                    this.key[acumulador] = verificar;
                    acumulador++;
                }
            }catch (IndexOutOfBoundsException ex){
                System.out.println(ex.getMessage());
            }
        }

        this.vigenereMatrix = crearMatrizVigenere();
    }

    private int pedirClave() throws IndexOutOfBoundsException{
        System.out.print("Ingrese la clave: ");
        int clave = lector.nextInt();
        if (clave > 64){
            throw new IndexOutOfBoundsException("Ingrese numeros menores a 64");
        }else {
            return clave;
        }
    }

    public BigVigenere(String numericKey) {
        this.vigenereMatrix = crearMatrizVigenere();

        this.key = new int[numericKey.length()];
        for (int i = 0; i < numericKey.length(); i++) {
            int index = CARACTERES.indexOf(numericKey.charAt(i));
            if (index != -1) {
                key[i] = index;
            } else {
                System.out.println("Carácter no válido en la clave.");
            }
        }
    }

    private char[][] crearMatrizVigenere() {
        char[][] matrix = new char[64][64];
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                int indice = (i + j) % CARACTERES.length();
                matrix[i][j] = CARACTERES.charAt(indice);
            }
        }
        return matrix;
    }

    public void imprimirKey() {
        for (int j : key) {
            System.out.println(j);
        }
    }

    public String encrypt(String message) {
        StringBuilder mensaje = new StringBuilder(message.length());
        int[] mensajeCopia = new int[message.length()];

        for (int i = 0; i < message.length(); i++) {
            int index = CARACTERES.indexOf(message.charAt(i));
            if (index != -1) {
                mensajeCopia[i] = index;
            }
        }

        int[] llaveAlargada = new int[message.length()];
        for (int i = 0; i < message.length(); i++) {
            llaveAlargada[i] = key[i % key.length];
        }

        for (int i = 0; i < message.length(); i++) {
            mensaje.append(vigenereMatrix[mensajeCopia[i]][llaveAlargada[i]]);
        }

        return mensaje.toString();
    }

    public String decrypt(String encryptedMessage) {
        StringBuilder mensajeDescifrado = new StringBuilder(encryptedMessage.length());

        for (int i = 0; i < encryptedMessage.length(); i++) {
            int claveActual = key[i % key.length];
            int indiceCifrado = CARACTERES.indexOf(encryptedMessage.charAt(i));

            int indiceOriginal = (indiceCifrado - claveActual) % 64;
            if (indiceOriginal < 0) {
                indiceOriginal += 64;
            }

            mensajeDescifrado.append(CARACTERES.charAt(indiceOriginal));
        }

        return mensajeDescifrado.toString();
    }

    public void reEncrypt() {
        System.out.print("Ingrese el mensaje encriptado: ");
        String mensajeEncriptado = lector.nextLine();

        String mensajeDescifrado = decrypt(mensajeEncriptado);
        System.out.println("Mensaje descifrado: " + mensajeDescifrado);

        System.out.print("Ingrese el tamano de la nueva clave: ");
        int nuevoTamano = lector.nextInt();
        System.out.print("Ingrese la nueva clave numérica: ");

        int[] nuevaClave = new int[nuevoTamano];
        int contador = 0;
        while (contador < nuevoTamano) {
            int valor = lector.nextInt();
            if (valor < 64) {
                nuevaClave[contador] = valor;
                contador++;
            } else {
                System.out.println("Ingrese números menores a 64");
            }
        }

        this.key = nuevaClave;
        String nuevoMensajeEncriptado = encrypt(mensajeDescifrado);
        System.out.println("Nuevo mensaje encriptado: " + nuevoMensajeEncriptado);
    }

    public char optimalSearch(int position) {
        int fila = position / 64;
        int columna = position % 64;
        return vigenereMatrix[fila][columna];
    }
}
