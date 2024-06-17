import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import java.util.Base64;
import java.io.*;

public class RSAModul implements kryptomodul {
    private PublicKey publicKey;
    private PrivateKey privateKey;
    
    public RSAModul() {
        generateKeys();
    }
    
    private void generateKeys() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair pair = keyGen.generateKeyPair();
            this.publicKey = pair.getPublic();
            this.privateKey = pair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void verschluesseln() {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            String plaintext = "Hello, World!";
            byte[] encrypted = cipher.doFinal(plaintext.getBytes());
            System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void entschluesseln() {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            String encryptedText = "Base64EncodedEncryptedTextHere"; // Replace with actual encrypted text
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            System.out.println("Decrypted: " + new String(decrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void savekey() {
        try (ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream("public.key"));
             ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream("private.key"))) {
            publicKeyOS.writeObject(publicKey);
            privateKeyOS.writeObject(privateKey);
            System.out.println("Keys saved to files.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void loadkey() {
        try (ObjectInputStream publicKeyIS = new ObjectInputStream(new FileInputStream("public.key"));
             ObjectInputStream privateKeyIS = new ObjectInputStream(new FileInputStream("private.key"))) {
            this.publicKey = (PublicKey) publicKeyIS.readObject();
            this.privateKey = (PrivateKey) privateKeyIS.readObject();
            System.out.println("Keys loaded from files.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
