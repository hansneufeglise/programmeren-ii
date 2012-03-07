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
        
        // Declareer variabelen
        InetAddress address;
        Socket socket;
        String name;        
        int port;
        
        // Valideer parameters
        if (args.length != 3 || !args[0].matches(Format.NAME) || !args[1].matches(Format.ADDRESS) || !args[2].matches(Format.PORT)) {
            System.out.println(USAGE);
            System.exit(0);
        }
    
        // Probeer hostname/ip te resolven en socket te starten
        try {
            name    = args[0];
            address = InetAddress.getByName(args[1]);
            port    = Integer.parseInt(args[2]);
            socket  = new Socket(address, port);
            
            // Socket proberen te openen op de server
            try {
                socket = new Socket(address, port);
                System.out.println("Client socket gestart op " + socket.getLocalSocketAddress());
            } catch (IOException e) {
                System.out.println("Kon geen socket maken op port " + port + " en adres " + address);
            }

            // Communicatie in twee richtingen starten
            try {
                Peer client = new Peer(name, socket);
                Thread streamInputHandler = new Thread(client);
                streamInputHandler.start();
                client.handleTerminalInput();
                client.shutDown();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        

        
    }

}

