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
    private static final String EXIT = "exit";

    /**
     * Constructor. Construeert een Peer-object met de gegeven naam
     * en de meegegeven sock. Initialiseert de input- en outputstreams.
     * @require (name != null) && (sock != null)
     * @param   naam naam van dit Peer-proces
     * @param   sock Socket van dit Peer-proces
     */
    public Peer(String name, Socket sock) throws IOException {
        this.name   = name;
        this.sock   = sock;
        this.in     = new BufferedReader(new InputStreamReader(System.in));
        this.out    = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    /**
     * Leest Strings uit de stream van de socket-verbinding en 
     * schrijft deze karakters naar de standard output. 
     */
    public void run() {
        String line;
        try {
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            while ((line = socketIn.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    
    /**
     * Leest Strings van de Terminal en stuurt deze Strings over de
     * socket-verbinding naar het Peer proces. Als Peer.EXIT wordt
     * ingetypt eindigt te methode.
     */
    public void handleTerminalInput() {
        String line;
        try {
            PrintWriter socketOut = new PrintWriter(new OutputStreamWriter(this.sock.getOutputStream()));
            while ((line = in.readLine()) != null) {
                if (line.equals(this.EXIT)) {
                    shutDown();
                } else {              
                    socketOut.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Verbreekt de verbinding. Beide streams worden afgesloten en
     * ook de Socket zelf wordt afgesloten.
     */
    public void shutDown() {
        try {
            in.close();
            out.close();
            sock.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
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
