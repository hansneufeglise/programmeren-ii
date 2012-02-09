package week4.cmdline;

import java.net.*;
import java.io.*;

/**
 * P2 prac wk4. <br>
 * Peer. Een klasse voor een Client en Server die over een Socket 
 * verbinding met elkaar kunnen praten via Terminal vensters.
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Peer implements Runnable {
    
    protected String            name;
    protected Socket            sock;
    protected BufferedReader    in;
    protected BufferedWriter    out;

    /**
     * Constructor. Construeert een Peer-object met de gegeven naam
     * en de meegegeven sock. Initialiseert de input- en outputstreams.
     * @require (name != null) && (sock != null)
     * @param   naam naam van dit Peer-proces
     * @param   sock Socket van dit Peer-proces
     */
    public Peer(String name, Socket sock) throws IOException 
    {
        // BODY NOG TOE TE VOEGEN
    }

    /**
     * Leest Strings uit de stream van de socket-verbinding en 
     * schrijft deze karakters naar de standard output. 
     */
    public void run() {
        // BODY NOG TOE TE VOEGEN
    }

    
    /**
     * Leest Strings van de Terminal en stuurt deze Strings over de
     * socket-verbinding naar het Peer proces. Als Peer.EXIT wordt
     * ingetypt eindigt te methode.
     */
    public void handleTerminalInput() {
        // BODY NOG TOE TE VOEGEN
    }

    /**
     * Verbreekt de verbinding. Beide streams worden afgesloten en
     * ook de Socket zelf wordt afgesloten.
     */
    public void shutDown() {
        // BODY NOG TOE TE VOEGEN
    }

    /** Levert de naam van dit Peer-object. */
    public String getName() {
        return name;
    }
    
    /** Leest een regel tekst van standaardinvoer. */
    static public String readString(String tekst) {
        System.out.print(tekst);
        String antw = null;
        try {
            BufferedReader in = 
                new BufferedReader(new InputStreamReader(System.in));            
            antw = in.readLine();
        } catch (IOException e) {
        }

        return (antw == null) ? "" : antw;
    }
}
