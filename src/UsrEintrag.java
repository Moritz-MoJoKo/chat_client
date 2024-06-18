package src; 

/**
 * Die Klasse Eintrag dient dem Speichern der User
 * @author Moritz Koch
 * @version 06.06.2024
 */
public class UsrEintrag
{
    private String name;
    private String passwort;

    /**
     * Konstruktor der Klasse Eintrag
     * @param name : String
     * @param passwort : String
     * @param id : String 
     */
    public UsrEintrag(String name, String passwort)
    {
        this.name = name;
        this.passwort = passwort;
    }

    /**
     * Diese Methode gibt das Passwort des Eintrages zur�ck
     * 
     * @return passwort : String
     */
    public String gibPasswort()
    {
        return this.passwort;
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
        
}
