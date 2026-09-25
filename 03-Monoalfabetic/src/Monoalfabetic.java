import java.util.ArrayList;
import java.util.Collections;

public class Monoalfabetic {

    private static final char[] MAJUSCULES = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M',
        'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T',
        'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    private char[] alfabetPermutat;

    public Monoalfabetic() {
        alfabetPermutat = permutaAlfabet(MAJUSCULES);
    }

    public static void main(String[] args) {
        Monoalfabetic mono = new Monoalfabetic();

        System.out.println("ALFABET ORIGINAL:");
        for (char c : MAJUSCULES) {
            System.out.print(c + " ");
        }

        System.out.println("\n");

        System.out.println("ALFABET PERMUTAT:");
        for (char c : mono.alfabetPermutat) {
            System.out.print(c + " ");
        }

        System.out.println("\n");
        
        // TEST 01
        String text1 = "Test 01 àrbitre, coixí, Perímetre";

        String xifrat1 = mono.xifraMonoAlfa(text1);
        String desxifrat1 = mono.desxifraMonoAlfa(xifrat1);

        System.out.println("Test 01:");
        System.out.println("Original:   " + text1);
        System.out.println("Xifrat:     " + xifrat1);
        System.out.println("Desxifrat:  " + desxifrat1);
        System.out.println("Correcte:   " + text1.equals(desxifrat1));

        System.out.println();

        // TEST 02
        String text2 = "Test 02 Taüll, DÍA, año";

        String xifrat2 = mono.xifraMonoAlfa(text2);
        String desxifrat2 = mono.desxifraMonoAlfa(xifrat2);

        System.out.println("Test 02:");
        System.out.println("Original:   " + text2);
        System.out.println("Xifrat:     " + xifrat2);
        System.out.println("Desxifrat:  " + desxifrat2);
        System.out.println("Correcte:   " + text2.equals(desxifrat2));

        System.out.println();

        // TEST 03
        String text3 = "Test 03 Peça, Òrrius, Bòvila";

        String xifrat3 = mono.xifraMonoAlfa(text3);
        String desxifrat3 = mono.desxifraMonoAlfa(xifrat3);

        System.out.println("Test 03:");
        System.out.println("Original:   " + text3);
        System.out.println("Xifrat:     " + xifrat3);
        System.out.println("Desxifrat:  " + desxifrat3);
        System.out.println("Correcte:   " + text3.equals(desxifrat3));
    }

    private int buscaPosicio(char caracter, char[] alfabet) {

        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == caracter) {
                return i;
            }
        }

        return -1;
    }

    public char[] permutaAlfabet(char[] alfabet) {
        ArrayList<Character> alfabetLlista = new ArrayList<>();
        for (char c : alfabet) {
            alfabetLlista.add(c);
        }
        Collections.shuffle(alfabetLlista);
        char[] resultat = new char[alfabetLlista.size()];

        for (int i = 0; i < alfabetLlista.size(); i++) {
            resultat[i] = alfabetLlista.get(i);
        }

        return resultat;
    }

    public String xifraMonoAlfa(String cadena) {
        StringBuffer resultat = new StringBuffer();

        for (int i = 0; i < cadena.length(); i++) {

            char caracter = cadena.charAt(i);
            boolean minuscula = Character.isLowerCase(caracter);
            char caracterMajuscula = Character.toUpperCase(caracter);
            int posicio = buscaPosicio(caracterMajuscula, MAJUSCULES);

            if (posicio != -1) {

                char xifrat = alfabetPermutat[posicio];

                if (minuscula) {
                    xifrat = Character.toLowerCase(xifrat);
                }
                resultat.append(xifrat);

            } else {
                resultat.append(caracter);
            }
        }

        return resultat.toString();
    }

    public String desxifraMonoAlfa(String cadena) {
        StringBuffer resultat = new StringBuffer();

        for (int i = 0; i < cadena.length(); i++) {

            char caracter = cadena.charAt(i);
            boolean minuscula = Character.isLowerCase(caracter);
            char caracterMajuscula = Character.toUpperCase(caracter);
            int posicio = buscaPosicio(caracterMajuscula, alfabetPermutat);

            if (posicio != -1) {

                char desxifrat = MAJUSCULES[posicio];

                if (minuscula) {
                    desxifrat = Character.toLowerCase(desxifrat);
                }
                resultat.append(desxifrat);

            } else {
                resultat.append(caracter);
            }
        }
        return resultat.toString();
    }
}
