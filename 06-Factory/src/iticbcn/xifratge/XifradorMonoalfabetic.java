package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class XifradorMonoalfabetic implements Xifrador{

    private static final char[] MAJUSCULES = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M',
        'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T',
        'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    private char[] alfabetPermutat;

    public XifradorMonoalfabetic() {
        alfabetPermutat = permutaAlfabet(MAJUSCULES);
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null)
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        return new TextXifrat(xifraMonoAlfa(msg).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null)
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        return desxifraMonoAlfa(xifrat.toString());
    }
}
