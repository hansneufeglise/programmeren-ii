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
        	server = new ServerSocket(port);
        } catch (IOException e) {
            mui.addMessage("Kon server niet starten op poort: " + port);
        }
        
        this.mui = mui;
        this.threads = new ArrayList<ClientHandler>();
    }

    /**
     * Luistert op de port van deze Server of er Clients zijn die
     * een verbinding willen maken. Voor elke nieuwe socketverbinding
     * wordt een nieuw ClientHandler thread opgestart die de verdere
     * communicatie met de Client afhandelt.
     */
    public void run() {
        try {
            while (true) {
            	Socket clientSocket = server.accept();

                // ClientHandler aanmaken die verkeer tussen server en client afhandelt            
                ClientHandler ch = new ClientHandler(this, clientSocket);
                addHandler(ch);
                ch.announce();
                ch.start();
            }            
        } catch (IOException e) {
            mui.addMessage("Kon verbinding met client niet openen.");
        }
    }

    /**
     * Stuurt een bericht via de collectie van aangesloten ClientHandlers
     * naar alle aangesloten Clients.
     * @param msg bericht dat verstuurd wordt
     */
    public void broadcast(String msg) {
        Iterator<ClientHandler> itr = this.threads.iterator();

        // Output server
        mui.addMessage(msg);
        
        // Output clients
        while (itr.hasNext()) {
        	itr.next().sendMessage(msg);
        }
    }

    /**
     * Voegt een ClientHandler aan de collectie van ClientHandlers toe.
     * @param handler ClientHandler die wordt toegevoegd
     */
    public void addHandler(ClientHandler handler) {
        this.threads.add(handler);
    }

    /**
     * Verwijdert een ClientHandler uit de collectie van ClientHandlers.
     * @param handler ClientHandler die verwijderd wordt
     */
    public void removeHandler(ClientHandler handler) {
        this.threads.remove(handler);
    }
    
    /** Sluit de socketverbinding van deze server. */
    public void shutdown() {
        this.broadcast("Shutting down server...");
        try {
            this.server.close();
        } catch (Exception e) { }
    }

} // end of class Server
