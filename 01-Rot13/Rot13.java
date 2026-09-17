public class Rot13 {

    char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M',
        'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T',
        'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    char[] minuscules = {
        'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è',
        'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm',
        'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't',
        'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };
    
    public static void main(String[] args) {
        System.out.println("Xifrat");
        System.out.println("------");
        System.out.println();

        System.out.println(new Rot13().xifraRot13("ABC"));
        System.out.println(new Rot13().xifraRot13("XYZ"));
        System.out.println(new Rot13().xifraRot13("Hola, Mr. calçot"));
        System.out.println(new Rot13().xifraRot13("Perdó, per tu què és"));

        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("------");

        System.out.println(new Rot13().desxifraRot13("IÏJ"));
        System.out.println(new Rot13().desxifraRot13("FGH"));
        System.out.println(new Rot13().desxifraRot13("Òwúi, Ùá. jiúkwb"));
        System.out.println(new Rot13().desxifraRot13("Zmálx, zmá bc acñ nà"));
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
        String rot13 = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int posicio = buscaPosicio(c, majuscules);

            if (posicio != -1) {
                rot13 += majuscules[(posicio + 13) % majuscules.length];
            } else {
                posicio = buscaPosicio(c, minuscules);
                if (posicio != -1) {
                    rot13 += minuscules[(posicio + 13) % minuscules.length];
                } else {
                    rot13 += c;
                }
            }
        }

        return rot13;
    }

    public String desxifraRot13 (String cadena) {
        String rot13 = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int posicio = buscaPosicio(c, majuscules);

            if (posicio != -1) {
                rot13 += majuscules[(posicio + majuscules.length - 13) % majuscules.length];
            } else {
                posicio = buscaPosicio(c, minuscules);
                if (posicio != -1) {
                    rot13 += minuscules[(posicio + minuscules.length - 13) % minuscules.length];
                } else {
                    rot13 += c;
                }
            }
        }
        return rot13;
    }

}
