import java.security.*;
import java.security.spec.*;
import javax.crypto.Cipher;
import java.util.Base64;
import java.io.*;

public class RSAKryptomodul implements kryptomodul {
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private static final String ALGORITHM = "RSA";
    
    @Override
    public void verschluesseln(String klartext, int key) {
        try {
            // Load public key
            loadkey();
            
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(klartext.getBytes());
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("Verschlüsselter Text: " + encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void entschluesseln(String geheimtext, int key) {
        try {
            // Load private key
            loadkey();
            
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(geheimtext));
            String decryptedText = new String(decryptedBytes);
            System.out.println("Entschlüsselter Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void savekey(int key) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("rsa_keypair.dat"))) {
            oos.writeObject(publicKey);
            oos.writeObject(privateKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void loadkey() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("rsa_keypair.dat"))) {
            publicKey = (PublicKey) ois.readObject();
            privateKey = (PrivateKey) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public KeyPair getKey() {
        return new KeyPair(publicKey, privateKey);
    }
    
    public void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
