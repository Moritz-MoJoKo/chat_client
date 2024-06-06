 
 
/**
 * Diese Klasse setzt das Entwurfsmuster DataTableGateway um. Dabei stellt es alle Datenbankrelevanten Funktionen, die die Anwendung benötigt 
 * zur Verfügung. Erweiterungen und Einschränkungen sind möglich.
 * 
 * @author Moritz Koch
 * @version 06.06.2024
 */
public class UsrGateway
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private DatabaseConnector db;

    /**
     * Konstruktor für Objekte der Klasse UsrGateway
     */
    public UsrGateway()
    {
        // Instanzvariable initialisieren
        db = null;
    }

    /**
     * Diese Methode setzt die READ-Funktion um, indem man nach einem Objekt mit einer bestimmten id fragen kann.
     * 
     * @param name : String
     * 
     * @return Eintragobjekt mit passendem namen oder null
     */
    public UsrEintrag hole(String name)
    {
        verbinde();
        db.executeStatement("SELECT * FROM user WHERE name ="+name);
        QueryResult ergebnis = db.getCurrentQueryResult();
        UsrEintrag erg = new UsrEintrag(ergebnis.getData()[0][0], ergebnis.getData()[0][1]);
        beende();
        return erg;
    }
    
    /**
     * Diese Methode setzt die READ-Funktion um, indem man sich alle Objekte der Tabelle liefern lassen kann.
     * 
     * @return Liste aller Einträge
     */
    public List<UsrEintrag> holeAlle()
    {
        verbinde();
        List <UsrEintrag> user = new List();
        db.executeStatement("Select name, passwort from user");
        QueryResult ergebnis = db.getCurrentQueryResult();
        if(ergebnis != null)
        {
            for(int i = 0; i < ergebnis.getRowCount(); i++)
            {
                user.append(new UsrEintrag(ergebnis.getData()[i][0], ergebnis.getData()[i][1]));
            }
        }
        beende();
        return user;
    }
    
    

    
    /**
     * Diese Methode setzt die CREATE-Funktion um, indem hier neue user in die Datenbank eingetragen werden.
     * 
     * @param name
     * @param passwort 
     */
    public void hinzufuegen(String name, String passwort)
    {
        verbinde();
        db.executeStatement("INSERT INTO user (name, passwort) VALUES ("+name+", "+passwort+")");
        beende();
    }
    
    
    /**
     * Diese Methode erzeugt die Tabelle user, wenn diese nicht schon exisitiert.
     */
    public void erzeugeTabelle()
    {
         verbinde();
         db.executeStatement("Create table if not exists user (name text PRIMARY KEY AUTOINCREMENT, passwort text)");
         beende();
    }
    
    /**
     * Diese Methode stellt eine Verbindung zur Datenbank her.
     */
    private void verbinde()
    {
        if(db == null)
        {
            db = new DatabaseConnector("",0,"userdb","","");
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
