import java.net.*;

/**
 * Klasse fuer einen ChatServer. Der ChatServer bietet die Möglickeit mit anderen verbundenen Clients einen Chat zu f�hren und mit ihnen zu schreiben
 * @author Moritz Koch, Erik
 * @version 04.06.2024
 */

public class ChatServer extends Server {

    private MsgGateway MsgHistorie;
    private UsrGateway UsrDB;
    private List<Spiel> spieleOnline; 
    
    public ChatServer(int p) {
        super(p);
        MsgHistorie = new MsgGateway();
        UsrDB = new UsrGateway();
        spieleOnline = new List<>();
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
     * Methode, die bei Aufruf eine Zufallszahl zwischen 0 und 20 zurück gibt.
     * @return Zufallszahl
     */
    private synchronized int gibZufallszahl()
    {
        return (int)(Math.random() * 20);
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
    private synchronized String gibTextbereich(String message)
    {
        String [] messageArray = message.split(" ");
        return messageArray[1];
    }
    
    
    /**
     * Diese Methode generiert einen String aus den Einträgen der übergebenen Liste.
     * Dabei beachtet man das folgende Format:
     * Name:Punkte Name:Punkte Name:Punkte usw.
     * 
     * @param Liste mit Objekten vom Typ Eintrag
     * @return String mit Einrtägen durch Leerzeichen getrennt
     */
    private String generiereStringAusList(List<Eintrag> l)
    {
        String ausgabe = "";
        l.toFirst();
        while(l.hasAccess()){
            ausgabe = ausgabe + l.getContent().gibName() + ":" + l.getContent().gibPunkte() + " ";
            l.next();
        }
        return ausgabe;
    }
}
