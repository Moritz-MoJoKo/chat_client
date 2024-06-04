package src;

/**
 * Die Klasse Eintrag dient dem Speichern der User
 * @author Moritz Koch
 * @version 04.06.2024
 */
public class UsrEintrag
{
    private int punkte;
    private String name;
    private String id;

    /**
     * Konstruktor der Klasse Eintrag
     * @param punkte : int
     * @param name : String
     */
    public UsrEintrag(String id, String name, int punkte)
    {
        this.id = id;
        this.punkte = punkte;
        this.name = name;
    }

    /**
     * Diese Methode gibt die Punkte des Eintrages zur�ck
     * 
     * @return punkte : int
     */
    public int gibPunkte()
    {
        return this.punkte;
    }
    
    /**
     * Diese Methode gibt den Namen des Eintrages zur�ck
     * 
     * @return name : String
     */
    public String gibName()
    {
        return this.name;
    }
    
    /**
     * Diese Methode gibt die id des Eintrages zur�ck
     */
    public String gibId()
    {
        return this.id;
    }
        
        
}
