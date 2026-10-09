import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
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

    public static byte[] getBytesXifrats(byte[] bIvIMsgXifrat) {
        byte[] msgXifrat = new byte[bIvIMsgXifrat.length - MIDA_IV];

        System.arraycopy(
            bIvIMsgXifrat, MIDA_IV,
            msgXifrat, 0,
            msgXifrat.length
        );

        return msgXifrat;
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception {

        // Obtenir els bytes de l’String
        byte[] bytesMsg = msg.getBytes(StandardCharsets.UTF_8);

        // Genera IV
        byte[] iv = generarIv();

        // Genera hash
        SecretKeySpec key = generaHash(clau);

        // Encrypt
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] msgXifrat = cipher.doFinal(bytesMsg);

        // Combinar IV i part xifrada
        byte[] resultat = new byte[iv.length + msgXifrat.length];

        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(msgXifrat, 0, resultat, iv.length, msgXifrat.length);

        return resultat;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

        // Extreure l'IV.
        byte[] iv = new byte[MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, 0, iv, 0, MIDA_IV);

        // Extreure la part xifrada.
        byte[] msgXifrat = getBytesXifrats(bIvIMsgXifrat);

        // Fer hash de la clau
        SecretKeySpec key = generaHash(clau);

        // Desxifrar.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);

        // return String desxifrat
        return new String(msgDesxifrat, StandardCharsets.UTF_8);
    }
}
