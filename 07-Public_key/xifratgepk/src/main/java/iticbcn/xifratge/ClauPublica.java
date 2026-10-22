package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        kg.initialize(2048);
        return kg.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE);
        byte[] clauBytes = msg.getBytes(StandardCharsets.UTF_8);
    } 

    public String desxifraRSA(byte[] masgXifrat, PrivateKey ClauPrivada){
        
    }
}
