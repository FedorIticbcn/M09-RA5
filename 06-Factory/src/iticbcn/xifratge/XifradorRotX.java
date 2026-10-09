package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

public class XifradorRotX implements Xifrador {
    private static final char[] MAJUSCULES = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M',
        'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T',
        'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    private static final char[] MINUSCLUES = {
        'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è',
        'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm',
        'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't',
        'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };

    private int buscaPosicio(char c, char[] alfabet) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private String xifraRotX (String cadena, int desplaçament) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int posicio = buscaPosicio(c, MAJUSCULES);
            if (posicio != -1) {
                sb.append(MAJUSCULES[(posicio + desplaçament) % MAJUSCULES.length]);
            } else {
                posicio = buscaPosicio(c, MINUSCLUES);
                if (posicio != -1) {
                    sb.append(MINUSCLUES[(posicio + desplaçament) % MINUSCLUES.length]);
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    private String desxifraRotX (String cadena, int desplaçament) {
        return xifraRotX(cadena, MAJUSCULES.length - desplaçament);
    }

    private String forçaBrutaRotX (String cadenaXifrada) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAJUSCULES.length; i++) {
            String possible = desxifraRotX(cadenaXifrada, i);
            String linia = "(" + i + ")->" + possible;
            System.out.println(linia);
            sb.append(linia).append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desp = parseClau(clau);
        return new TextXifrat(xifraRotX(msg, desp).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desp = parseClau(clau);
        return desxifraRotX(xifrat.toString(), desp);
    }

    private int parseClau(String clau) throws ClauNoSuportada {
        try {
            int n = Integer.parseInt(clau);
            if (n < 0 || n >= MAJUSCULES.length)
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a " + (MAJUSCULES.length - 1));
            return n;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a " + (MAJUSCULES.length - 1));
        }
    }
}
