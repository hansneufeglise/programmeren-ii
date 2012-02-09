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
    private static final String USAGE =
        "usage: java week4.cmdline.Server <name> <port>" ;

    /** Start een Server-applicatie op. */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(0);
        }

        String name = args[0];
        int   port  = 0;

        // Nog toe te voegen: controle en parsen van de 
        // .. argumentlijst args. Daarna hebben name en port 
        // .. een gedefinieerde waarde. 
        // .. Daarna wachten tot een Client zich aanmeldt.
        // .. Vervolgens Peer-object creeeren en zorgen dat
        // .. de input op de Terminal goed wordt afgehandeld.
      }

} // end of class Server
