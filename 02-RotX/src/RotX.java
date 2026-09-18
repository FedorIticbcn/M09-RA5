public class RotX {
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

    public static void main(String[] args) {
        RotX rotX = new RotX();
        String[] cadenas = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què es", "ZAÁ", "Ïqoc, Óú. écoèqü", "Úiüht, úiü wx ùxì iv"};
        System.out.println("Xifrat");
        System.out.println("------");
        System.out.println();

        System.out.println(rotX.xifraRotX(cadenas[0], 0));
        System.out.println(rotX.xifraRotX(cadenas[1], 2));
        System.out.println(rotX.xifraRotX(cadenas[2], 4));
        System.out.println(rotX.xifraRotX(cadenas[3], 6));

        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("------");

        System.out.println(rotX.desxifraRotX(cadenas[0], 0));
        System.out.println(rotX.desxifraRotX(cadenas[4], 2));
        System.out.println(rotX.desxifraRotX(cadenas[5], 4));
        System.out.println(rotX.desxifraRotX(cadenas[6], 6));
    
        System.out.println();
        System.out.println("Missatge xifrat: " + cadenas[6]);
        System.out.println("------");

        rotX.forçaBrutaRotX(cadenas[6]);
    }

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
}
