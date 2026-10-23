import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    
    public int npass = 0;

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for(int i=0; i< aHashes.length; i++){
            System.out.printf("================================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("-------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("-------------------------\n\n");
        }

    public String getSHA512AmbSalt(String pw, String salt) {
        String hashString = pw + salt;
        String result = "";
        byte[] hashBytes;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            hashBytes = messageDigest.digest(hashString.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e); 
        }

        HexFormat hex = HexFormat.of();
        result = hex.formatHex(hashBytes);
        return result;
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        PBEKeySpec pbekeyspec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), 65536, 512);
        SecretKey secretKey;
        String result = "";
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            secretKey = secretKeyFactory.generateSecret(pbekeyspec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        byte[] secretKeyBytes = secretKey.getEncoded();
        HexFormat hex = HexFormat.of();
        result = hex.formatHex(secretKeyBytes);
        return result;
    }

    public String forcaBruta(String alg, String hash, String salt) {

    }

    public String getInterval(long t1, long t2) {

    }

}
