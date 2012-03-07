package week4.cmdline;

import java.net.*;
import java.io.*;

/**
 * P2 prac wk4. <br>
 * Server. Simpele Server-klasse die een Socket-connectie opzet met een 
 * Client, waarna beide objecten via de Terminal met elkaar kunnen praten.
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Server {
    private static final String USAGE = "usage: java week4.cmdline.Server <name> <port>";

    /** Start een Server-applicatie op. */
    public static void main(String[] args) {
        // Valideer parameters
        if (args.length != 2 || !args[0].matches(Format.NAME) || !args[1].matches(Format.PORT)) {
            System.out.println(USAGE);
            System.exit(0);
        }
        
        String name = args[0];
        int port = Integer.parseInt(args[1]);
        ServerSocket serverSocket = null;
        
        // Server starten
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server gestart op " + serverSocket.getLocalSocketAddress());                        
        } catch (IOException e) {
            System.err.println("Kon server niet starten op poort: " + port);
            System.exit(1);
        }
 
        // Verbindingen van clients toestaan                
        Socket clientSocket = null;
        try {
            int clientCount = 0;
            while (true) {
                clientSocket = serverSocket.accept();                
                System.out.println("Client #" + (++clientCount) + " is verbonden.");
                // Communicatie in twee richtingen starten            
                Peer client = new Peer(name, clientSocket);
                Thread streamInputHandler = new Thread(client);
                streamInputHandler.start();
                client.handleTerminalInput();
                client.shutDown();                
            }            
        } catch (IOException e) {
            System.err.println("Kon verbinding met client niet openen.");
            System.exit(1);
        }


      }

}
