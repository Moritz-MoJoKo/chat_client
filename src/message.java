 

 

 


/**
 * Mit dieser Klasse werden Objekte der Klasse Message geschaffen und implementiert.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class message
{
    // Instanzvariablen
    private String autor;
    private String content;
    private String date;

    /**
     * Konstruktor f�r Objekte der Klasse message
     */
    public message(String autor, String content, String date)
    {
        this.autor=autor;
        this.content=content;
        this.date=date;
    }

    /**
     * Diese Methode gibt den autor einer Nachricht zur�ck
     * 
     * @return autor : String
       */
    public String getAutor(){
        return this.autor;
    }
    
    /**
     * Diese Methode gibt den Inhalt einer Nachricht zur�ck
     * 
     * @return content : String
       */
    public String getContent(){
        return this.content;
    }
    
    /**
     * Diese Methode gibt das Datum einer Nachricht zur�ck
     * 
     * @return date : String
       */
    public String getDate(){
        return this.date;
    }
}
