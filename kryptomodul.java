


import java.security.KeyPair;

/**
 * Beschreiben Sie hier die Klasse kryptomodul.
 * 
 * @author Moritz Koch
 * @version 13.06.2024
 */
public interface kryptomodul {

    public void verschluesseln(String klartext);
    
    public void entschluesseln(String geheimtext);
    
    public void savekey();
    
    public void loadkey();
    
    public KeyPair getKey();
}
