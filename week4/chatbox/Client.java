package week4.chatbox;

import java.net.*;
import java.io.*;

/**
 * P2 prac wk4. <br>
 * Client. Een Thread-klasse voor het onderhouden van een 
 * Socket-verbinding met een Server. De Thread leest berichten uit
 * de socket en stuurt die door naar zijn MessageUI.
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Client extends Thread {

    private String          clientName;
    private MessageUI       mui;
    private Socket          sock;
    private BufferedReader  in;
    private PrintWriter     out;

    /**
     * Construeert een Client-object en probeert een socketverbinding
     * met een Server op te starten.
     */
    public Client(String name, InetAddress host, int port, MessageUI mui) throws IOException {
        this.mui = mui;
        this.clientName = name;
        
        // Probeer socketverbinding met server te starten
        try {
            this.sock = new Socket(host, port);
            System.out.println("Client socket gestart op " + socket.getLocalSocketAddress());            
        } catch (IOException e) {
            System.out.println("Kon geen socket maken op port " + port + " en adres " + address);
            System.exit(1);
        }
        
        // ...
        this.in     = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
        this.out    = new PrintWriter(new OutputStreamWriter(this.sock.getOutputStream()));
        
        // Eerste wat de client doet is zijn naam verzenden:
        sendMessage(this.name);
    }

    /**
     * Leest berichten uit de socketverbinding. Elk berichtje wordt
     * gestuurd naar de MessageUI van deze Client. Als er iets fout
     * gaat bij het lezen van berichten, sluit deze methode de
     * socketverbinding.
     */
    public void run() {
        try {
            while (true) {
                if (this.in.ready()) {
                    mui.addMessage(in.readLine());
                }
            }
        } catch (IOException e) {
            shutdown();
        }
    }

    /** Stuurt een bericht over de socketverbinding naar de ClientHandler. */
    public void sendMessage(String msg) {
        out.println(msg);
    }

    /** Sluit de socketverbinding van deze client. */
    public void shutdown() {
        mui.addMessage("Closing socket connection...");

        try {
            sock.shutdownInput();
            sock.shutdownOutput();
            sock.close();   
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /** Levert de naam van de gebruiker van deze Client. */
    public String getClientName() {
        return clientName;
    }

} // end of class Client
