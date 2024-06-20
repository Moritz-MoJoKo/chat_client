
import java.net.*;
/**
 * Klasse fuer einen ChatClient.
 * 
 *          C=>S                            |             S=>C
 *                                          |
 *      Verbinden                           |      +OK Verbindung hergestellt
 *      REG <user> <pw>                     |      REG Registrierung erfolgreich
 *                                          |      ERR01 User schon vergeben
 *                                          |      ERR02 Passwort zu kurz (mind. 8 Zeichen)
 *      ANM <user> <pw>                     |      ANM Anmeldung erfolgreich
 *                                          |      ERR03 Daten unpassend
 *      SND <msg>                           |      +OK Nachricht wird versendet
 *                                          |      ERR04 Nachricht zu lang (höch. 100 Zeichen) 
 *      HIS                                 |      +OK Historie wird geladen
 *      END                                 |      END user ist jetzt offline
 *      
 *      
 * @author 
 * @version 
 */

public class ChatServer extends Server {

    private MsgGateway msgDB;
    private UsrGateway usrDB;
    
    
    public ChatServer(int p) {
        super(p);
        msgDB = new MsgGateway();
        usrDB = new UsrGateway();
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Der angemeldete Client bekommt die Meldung, dass er angenommen wurde
     * und verbunden ist.
     */

    public void processNewConnection(String pClientIP, int pClientPort){
        this.send(pClientIP, pClientPort, "+OK Verbindung hergestellt");
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Der angemeldete Client bekommt die gesendete Meldung zurueckgeschickt.
     * Und das Protokoll wird hiermit implementiert.
     */
    public void processMessage(String pClientIP, int pClientPort, String pMessage){ 
        switch(gibBefehlsbereich(pMessage))
        {   
            case "REG":
            {
                if(usrDB.istNameVorhanden(this.wortAn(pMessage, 1))){
                    //Wenn ja ERR01
                    this.send(pClientIP, pClientPort, "ERR01 Name schon vergeben");
                }
                else{
                    if(this.wortAn(pMessage, 2).length() < 8){
                        this.send(pClientIP, pClientPort, "ERR02 Passwort zu kurz (mind. 8 Zeichen)");
                    }
                    else{
                        this.send(pClientIP, pClientPort, "REG Registrierung erfolgreich");
                        usrDB.hinzufuegen(this.wortAn(pMessage, 1), this.wortAn(pMessage, 2));
                    }
                }
                    
                
                break;
            }
            
            case "ANM":
            {
                if(usrDB.passenDaten(this.wortAn(pMessage, 1), this.wortAn(pMessage,2))){
                    this.send(pClientIP, pClientPort, "ERR03 Daten unpassend");
                }
                else{
                    this.send(pClientIP, pClientPort, "ANM Anmeldung erfolgreich");
                }
                break;
            }
                
            case "SND":
            {
                String message = "";
                if(pMessage.length() >= 104){
                    this.send(pClientIP, pClientPort, "ERR04 Nachricht zu lang (höch. 100 Zeichen)");
                }
                else{
                    this.send(pClientIP, pClientPort, "+OK Nachricht wird versendet");
                    message = pMessage.substring(4); 
                    msgDB.postMessage(message);
                }
                break;
            }
            
            case "HIS":
            {
                msgDB.ladeHistory();
                this.send(pClientIP, pClientPort, "+OK Historie wird geladen");
                break;
            }
            
            case "END":
            {
                this.send(pClientIP, pClientPort, "END User ist jetzt offline");
                break;
            }
                
            default:
            {
                this.send(pClientIP, pClientPort, "-E0 Falsche Angaben"); 
                break;
            }
        }

    }   
    
    /**
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Die Verbindung wird beendet und aus der Liste der Clients gestrichen.
     */
    public void processClosingConnection(String pClientIP, int pClientPort){
        this.send(pClientIP, pClientPort, "EXT complete");
        this.closeConnection(pClientIP, pClientPort);
    }
    
    /**
     * Main-Methode die den Server auf Port 1024 startet.
     */
    public static void main(String [] args)
    {
        ChatServer es = new ChatServer(2000);
    }
    
    /**
     * Diese Methode gibt den Befehl zurück die die message beinhaltet
     * 
     * @param message
     * 
     * @return Befehl
     */
    private synchronized String gibBefehlsbereich(String message)
    {
        return message.split(" ")[0];
    }

    /**
     * Diese Methode gibt den Text zurück die die message beinhaltet
     * 
     * @param message
     * 
     * @return Text
     */
    private synchronized String wortAn(String message, int stelle)
    {
        return message.split(" ")[stelle];
    }
    
    
   
}

