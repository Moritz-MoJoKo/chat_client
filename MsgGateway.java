/**
 * Diese Klasse setzt das Entwurfsmuster DataTableGateway um. Dabei stellt es alle Datenbankrelevanten Funktionen, die die Anwendung benötigt 
 * zur Verfügung. Erweiterungen und Einschränkungen sind möglich.
 * 
 * @author 
 * @version 
 */
public class MsgGateway
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private DatabaseConnector db;

    /**
     * Konstruktor für Objekte der Klasse HighscoreGateway
     */
    public MsgGateway()
    {
        // Instanzvariable initialisieren
        db = null;
    }

    
    
    /**
     * Diese Methode setzt die CREATE-Funktion um, indem hier neue Nachrichten in die Datenbank eingetragen werden.
     * 
     * @param name
     * @param punkte
     */
    public void postMessage(String msg)
    {
        verbinde();
        db.executeStatement("INSERT INTO msgHistorie (msg, datum) VALUES ('"+msg+"', datetime()");
        beende();
    }
    
    public List<message> ladeHistory(){
        List <message> messageHis = new List();
        db.executeStatement("Select autor, msg, datum from msgHistorie");
        QueryResult ergebnis = db.getCurrentQueryResult();
        if(ergebnis != null)
        {
            for(int i = 0; i < ergebnis.getRowCount(); i++)
            {
                messageHis.append(new message(ergebnis.getData()[i][0], ergebnis.getData()[i][1], ergebnis.getData()[i][2]));
            }
        }
        beende();
        return messageHis;
    }
    
    
    /**
     * Diese Methode erzeugt die Tabelle highscore, wenn diese nicht schon exisitiert.
     */
    public void erzeugeTabelle()
    {
         verbinde();
         db.executeStatement("Create table if not exists msgHistorie (autor text PRIMARY KEY AUTOINCREMENT, msg text, datum text)");
         beende();
    }
    
    /**
     * Diese Methode stellt eine Verbindung zur Datenbank her.
     */
    private void verbinde()
    {
        if(db == null)
        {
            db = new DatabaseConnector("",0,"msgDb","","");
        }
    }
    
    /**
     * Diese Methode beendet die Verbindung zur Datenbank.
     */
    private void beende()
    {
        if(db != null)
        {
            db.close();
            db = null;
        }
    }
    
}
