import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Agora illa Otoro"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] xifrats = null;
            String desxifrat = "";
            try {
                xifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(xifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }

            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("ENC: " + new String(xifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] generarIv() {
        byte[] iv = new byte[MIDA_IV];

        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        return iv;
    }


    public static SecretKeySpec generaHash(String clau) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] clauBytes = clau.getBytes(StandardCharsets.UTF_8);
        byte[] hash = digest.digest(clauBytes);

        SecretKeySpec key = new SecretKeySpec(hash, "AES");

        return key;
    }


    public static byte[] xifraAES(String msg, String clau)
        throws Exception {

        //Obtenir els bytes de l’String
        byte[] byteCadena = msg.getBytes("UTF-8");

        // Genera IvParameterSpec
        byte[] iv = generarIv();

        // Genera hash

        // Encrypt.

        // Combinar IV i part xifrada.

        // return iv+msgxifrat
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau)
        throws Exception {

        // Extreure l'IV.

        // Extreure la part xifrada.

        // Fer hash de la clau

        // Desxifrar.

        // return String desxifrat
    }
}
