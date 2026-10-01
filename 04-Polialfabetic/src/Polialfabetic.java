import java.util.ArrayList;
import java.util.Collections;

public class Polialfabetic {

    private static final char[] MAJUSCULES = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M',
        'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T',
        'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    private static char[] alfabetPermutat;

    public static void main(String[] args) {
        String messages[] = {
            "Test 01 àrbitre, coixi, Perímtre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };
        String messagesXifrats[] = new String[messages.length];
        
        System.out.println("Xifratge:\n--------");
        for(int i = 0; i < messages.length; i++) {
            initRandom(MAJUSCULES);
            messagesXifrats[i] = xifraPoliAlfa(messages[i]);
            System.out.printf("%-34s -> %s%n", messages[i], messagesXifrats[i]);
        }

        System.out.println("Desxifratge:\n--------");
        for(int i = 0; i < messages.length; i++) {
            initRandom(MAJUSCULES);
            String message = desxifraPoliAlfa(messagesXifrats[i]);
            System.out.printf("%-34s -> %s%n", messagesXifrats[i], message);
        }
    }

    private static void initRandom(char[] alfabet) {
        permutaAlfabet(alfabet);
    }

    private static int buscaPosicio(char caracter, char[] alfabet) {

        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == caracter) {
                return i;
            }
        }

        return -1;
    }

    public static void permutaAlfabet(char[] alfabet) {
        ArrayList<Character> alfabetLlista = new ArrayList<>();
        for (char c : alfabet) {
            alfabetLlista.add(c);
        }
        Collections.shuffle(alfabetLlista);
        char[] resultat = new char[alfabetLlista.size()];

        for (int i = 0; i < alfabetLlista.size(); i++) {
            resultat[i] = alfabetLlista.get(i);
        }

        alfabetPermutat = resultat;

        // return resultat;
    }

    public static String xifraPoliAlfa(String msg) {
        StringBuffer resultat = new StringBuffer();

        for (int i = 0; i < msg.length(); i++) {

            char caracter = msg.charAt(i);
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

    public static String desxifraPoliAlfa(String msgXifrat) {
        StringBuffer resultat = new StringBuffer();

        for (int i = 0; i < msgXifrat.length(); i++) {

            char caracter = msgXifrat.charAt(i);
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
