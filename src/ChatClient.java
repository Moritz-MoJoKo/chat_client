 

import javax.swing.*;
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
 *      HIS                                 |      HIS Historie wird geladen
 *      END                                 |      END <user> ist jetzt offline
 *      
 *      
 * @author 
 * @version 
 */
public class ChatClient extends Client { 
    public ChatClient(String ip, int p) {
        super(ip, p);
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Der Client gibt die erhaltende Meldung, auf dem Textfeld aus.
     */

    public void processMessage(String message){
        switch(gibBefehlsbereich(message))
        {
            case "+OK":
            {
                System.out.println(gibTextbereich(message));
                break;
            }
            
            case "REG":
            {
                System.out.println(gibTextbereich(message));
                break;
            }
            
            case "ANM":
            {
                System.out.println(gibTextbereich(message));
                break;
            }
                
            case "SND":
            {
                System.out.println(message);
                break;
            }
            
            case "HIS":
            {
                System.out.println(gibTextbereich(message));    
                break;
            }
            
            case "END":
            {
                System.out.println(gibTextbereich(message));
                break;
            }
            
            default:
            {
                System.out.println("Befehl falsch. Bitte richtigen Befehl eintippen.");
                break;
            }
        }
    }

    /**
     * Diese Methode gibt den Befehl zurück die die message beinhaltet
     * 
     * @param message
     * 
     * @return Befehl
     */
    private String gibBefehlsbereich(String message)
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
    private String gibTextbereich(String message)
    {
        String [] messageArray = message.split(" ");
        String text = "";
        for(int i = 1; i < messageArray.length; i++)
        {
            text = text+" "+ messageArray[i];
        }
        return text;
    }

    /**
     * Diese Methode druckt die Higscoreliste auf der Konsole aus.
     * @param message
     */
    private void highscorelisteDrucken(String message)
    {
        String [] plaetze = message.split(" ");
        for(int i = 1; i < plaetze.length; i++)
        {
            String [] eintrag = plaetze[i-1].split(":");
            System.out.println(i+". "+eintrag[0]+"\t : \t"+eintrag[1]);
        }
    }
}
