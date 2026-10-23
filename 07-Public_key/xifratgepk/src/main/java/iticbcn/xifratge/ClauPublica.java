package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {

    public static final String RSA = "RSA";
    
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance(RSA);
        kg.initialize(2048);
        return kg.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);

        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);

        return cipher.doFinal(msgBytes);
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey сlauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, сlauPrivada);

        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);

        return new String(msgDesxifrat, StandardCharsets.UTF_8);
    }
}
