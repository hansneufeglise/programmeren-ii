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

        try {
            // Probeer een nieuwe socket te starten op de server            
            ServerSocket serverSocket = new ServerSocket(port);            
            System.out.println("Server gestart op " + serverSocket.getLocalSocketAddress());
            int clientCount = 1;
            while (true) {
                // Verbindingen van clients toestaan                
                Socket clientSocket = serverSocket.accept();                
                System.out.println("Client #" + clientCount + " is verbonden.");      
                clientCount++;
                Peer client = new Peer(name, clientSocket);
                Thread streamInputHandler = new Thread(client);
                streamInputHandler.start();
                client.handleTerminalInput();
                 // shutdown !?
                client.shutDown();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        /*
        De klasse Server is een klasse met alleen een methode main. Net als bij de klasse Client
        worden de Socket sock van de server en een Peer-object en in deze methode main gecon-
        strueerd. De Server applicatie wordt als volgt opgestart:
            java week4.cmdline.Server <name> <port>.
        Anders dan bij de klasse Client moet er eerst een ServerSocket object gecree ̈erd
        worden en blijft de Server wachten totdat een Client verbinding zoekt met de Server over
        de port.
        */

        // Nog toe te voegen: controle en parsen van de 
        // .. argumentlijst args. Daarna hebben name en port 
        // .. een gedefinieerde waarde. 
        // .. Daarna wachten tot een Client zich aanmeldt.
        // .. Vervolgens Peer-object creeeren en zorgen dat
        // .. de input op de Terminal goed wordt afgehandeld.
  
      }

}
