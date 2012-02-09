package week4.cmdline;

import java.net.*;
import java.io.*;

/**
 * P2 prac wk4. <br>
 * Client. Simpele Client-klasse die een Socket-connectie opzet met een 
 * Server, waarna beide objecten via de Terminal met elkaar kunnen praten.
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Client {
    private static final String USAGE =
        "usage: java week4.cmdline.Client <name> <address> <port>" ;

    /** Start een Client-applicatie op. */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println(USAGE);
            System.exit(0);
        }
    
        String      name = args[0];
        InetAddress addr = null;
        int         port = 0;
        Socket      sock = null;

        // Nog toe te voegen: controle en parsen van de 
        // .. argumentlijst args. Daarna hebben name, addr 
        // .. en port een gedefinieerde waarde.

        // try to open a Socket to the server
        try {
            sock = new Socket(addr, port);
        } catch (IOException e) {
            System.out.println("ERROR: could not create a socket on " +
                                addr + " and port " + port);
        }
 
        // create Peer object and start the two-way communication
        try {
            Peer client = new Peer(name, sock);
            Thread streamInputHandler = new Thread(client);
            streamInputHandler.start();
            client.handleTerminalInput();
            client.shutDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

} // end of class Client

