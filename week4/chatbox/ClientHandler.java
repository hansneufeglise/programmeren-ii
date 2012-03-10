package week4.chatbox;

import java.io.*;
import java.net.*;

/**
 * P2 prac wk4.
 * ClientHandler. Een klasse voor het onderhouden van een 
 * socketverbinding tussen een Client en een Server.
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class ClientHandler extends Thread {

    private Server           server;
    private Socket           sock;
    private BufferedReader   in;
    private PrintWriter      out;
    private String           clientName;

    /**
     * Construeert een ClientHandler object.
     * Initialiseert de beide Data streams.
     * @require server != null && sock != null
     */
    public ClientHandler(Server server, Socket sock) throws IOException {
        this.server = server;
        this.sock = sock;
        
        // Initialiseert beide datastreams
        this.in     = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
        this.out    = new PrintWriter(new OutputStreamWriter(this.sock.getOutputStream()));
    }

    /**
     * Leest de naam van de Client uit de inputstream en stuurt de
     * een broadcast-berichtje naar de Server om te melden dat de
     * Client nu deelneemt aan de chatbox. Merk op dat deze methode
     * meteen na het construeren van een ClientHandler dient te
     * worden aangeroepen.
     */
    public void announce() throws IOException {
   		clientName = in.readLine();
   		server.broadcast("[" + clientName + " has entered]");
    }

    /**
     * Deze methode zorgt voor het doorzenden van berichten van
     * de Client. Aan elk berichtje dat ontvangen wordt, wordt de
     * naam van de Client toegevoegd en het nieuwe berichtje wordt
     * ter broadcast aan de Server aangeboden. Als er bij het lezen 
     * van een bericht een IOException gegooid wordt, concludeert 
     * de methode dat de socketverbinding is verbroken en wordt
     * shutdown() aangeroepen.
     */
    public void run() {
        try {
            while (true) {
            	server.broadcast("[" + clientName + "] " + in.readLine());
            }
        } catch (IOException e) {
            shutdown();
        }
    }

    /**
     * Deze methode kan gebruikt worden om een bericht over de 
     * socketverbinding naar de Client te sturen. Niet
     * bereikbare clients worden al via de run-methode
     * afgehandeld.
     */
    public void sendMessage(String msg) {
		out.println(msg);
        out.flush();
    }

    /**
     * Deze ClientHandler meldt zich af bij de Server en stuurt
     * vervolgens een laatste broadcast naar de Server om te melden
     * dat de Client niet langer deelneemt aan de chatbox.
     */
    private void shutdown() {
        server.removeHandler(this);
        server.broadcast("[" + clientName + " has left]");
    }

} // end of class ClientHandler
