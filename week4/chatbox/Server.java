package week4.chatbox;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * P2 prac wk5. <br>
 * Server. Een Thread-klasse die luistert naar socketverbindingen op
 * gespecificeerde port. Voor elke socketverbinding met een Client wordt
 * een nieuwe ClientHandler thread opgestart.
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Server extends Thread {
    private int                        port;
    private MessageUI                  mui;
    private Collection<ClientHandler>  threads;
    private ServerSocket server;

    /** Construeert een nieuw Server-object. */
    public Server(int port, MessageUI mui) {
        // Server starten
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server gestart op " + serverSocket.getLocalSocketAddress());                        
        } catch (IOException e) {
            System.err.println("Kon server niet starten op poort: " + port);
            System.exit(1);
        }
        
        this.mui = mui;
        threads = new Collection<ClientHandler>();
    }

    /**
     * Luistert op de port van deze Server of er Clients zijn die
     * een verbinding willen maken. Voor elke nieuwe socketverbinding
     * wordt een nieuw ClientHandler thread opgestart die de verdere
     * communicatie met de Client afhandelt.
     */
    public void run() {
        Socket clientSocket = null;
        try {
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

    /**
     * Stuurt een bericht via de collectie van aangesloten ClientHandlers
     * naar alle aangesloten Clients.
     * @param msg bericht dat verstuurd wordt
     */
    public void broadcast(String msg) {
        // BODY NOG TOE TE VOEGEN
    }

    /**
     * Voegt een ClientHandler aan de collectie van ClientHandlers toe.
     * @param handler ClientHandler die wordt toegevoegd
     */
    public void addHandler(ClientHandler handler) {
        // BODY NOG TOE TE VOEGEN
    }

    /**
     * Verwijdert een ClientHandler uit de collectie van ClientHandlers.
     * @param handler ClientHandler die verwijderd wordt
     */
    public void removeHandler(ClientHandler handler) {
        // BODY NOG TOE TE VOEGEN
    }

} // end of class Server
