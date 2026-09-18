package src;
public class Rot13 {

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
        Rot13 rot13 = new Rot13();
        System.out.println("Xifrat");
        System.out.println("------");
        System.out.println();

        System.out.println(rot13.xifraRot13("ABC"));
        System.out.println(rot13.xifraRot13("XYZ"));
        System.out.println(rot13.xifraRot13("Hola, Mr. calçot"));
        System.out.println(rot13.xifraRot13("Perdó, per tu què és"));

        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("------");

        System.out.println(rot13.desxifraRot13("IÏJ"));
        System.out.println(rot13.desxifraRot13("FGH"));
        System.out.println(rot13.desxifraRot13("Òwúi, Ùá. jiúkwb"));
        System.out.println(rot13.desxifraRot13("Zmálx, zmá bc acñ nà"));
    }

    private int buscaPosicio(char c, char[] alfabet) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public String xifraRot13 (String cadena) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int posicio = buscaPosicio(c, MAJUSCULES);

            if (posicio != -1) {
                sb.append(MAJUSCULES[(posicio + 13) % MAJUSCULES.length]);
            } else {
                posicio = buscaPosicio(c, MINUSCLUES);
                if (posicio != -1) {
                    sb.append(MINUSCLUES[(posicio + 13) % MINUSCLUES.length]);
                } else {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }

    public String desxifraRot13 (String cadena) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int posicio = buscaPosicio(c, MAJUSCULES);

            if (posicio != -1) {
                sb.append(MAJUSCULES[(posicio + MAJUSCULES.length - 13) % MAJUSCULES.length]);
            } else {
                posicio = buscaPosicio(c, MINUSCLUES);
                if (posicio != -1) {
                    sb.append(MINUSCLUES[(posicio + MINUSCLUES.length - 13) % MINUSCLUES.length]);
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

}
