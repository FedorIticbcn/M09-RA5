package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador{

    private static final char[] MAJUSCULES = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M',
        'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T',
        'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    private static char[] alfabetPermutat;
    private static Random random;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        initRandom(clau);
        return new TextXifrat(xifraPoliAlfa(msg).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        initRandom(clau);
        return desxifraPoliAlfa(xifrat.toString());
    }

    private void initRandom(String clau) throws ClauNoSuportada {
        try {
            random = new Random(Long.parseLong(clau));
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
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

        Collections.shuffle(alfabetLlista, random);
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

                permutaAlfabet(MAJUSCULES);

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

            if (buscaPosicio(caracterMajuscula, MAJUSCULES) != -1) {

                permutaAlfabet(MAJUSCULES);
                int posicio = buscaPosicio(caracterMajuscula, alfabetPermutat);
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
