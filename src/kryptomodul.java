import java.security.KeyPair;

/**
 * Beschreiben Sie hier die Klasse kryptomodul.
 * 
 * @author Moritz Koch
 * @version 13.06.2024
 */
public interface kryptomodul {

    public void verschluesseln(String klartext, int key);
    
    public void entschluesseln(String geheimtext, int key);
    
    public void savekey(int key);
    
    public void loadkey();
    
    public KeyPair getKey();
}
