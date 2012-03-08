package week4.chatbox;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClientGUI extends JFrame implements ActionListener, MessageUI {

    private JButton     bConnect;
    private JTextField  tfAddress;
    private JTextField  tfPort;
    private JTextField  tfName;
    private JTextField  tfMyMessage;
    private JTextArea   taMessages;
    private Client      client;
    
    /** Construeert een ClientGUI object. */
    public ClientGUI() {
        super("Client GUI");
        
        buildGUI();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    e.getWindow().dispose();
                }
                public void windowClosed(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
    }

    /** Bouwt de daadwerkelijke GUI. */
    private void buildGUI() {
        setSize(440,440);

        // Panel p1 - Listen

        JPanel p1 = new JPanel(new FlowLayout());
        JPanel pp1 = new JPanel(new GridLayout(3, 2));

        JLabel lbAddress = new JLabel("Address: ");
        tfAddress = new JTextField("localhost");

        JLabel lbPort = new JLabel("Port:");
        tfPort        = new JTextField("2727");

        JLabel lbName = new JLabel("Name:");
        tfName        = new JTextField(12);

        pp1.add(lbAddress);
        pp1.add(tfAddress);
        pp1.add(lbPort);
        pp1.add(tfPort);
        pp1.add(lbName);
        pp1.add(tfName);
        
        bConnect = new JButton("Connect");
        bConnect.setEnabled(false);
        bConnect.addActionListener(this);

        p1.add(pp1, BorderLayout.WEST);
        p1.add(bConnect, BorderLayout.EAST);

        // Panel p2 - My message
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());

        JLabel lbMyMessage = new JLabel("My message:");
        tfMyMessage = new JTextField(34);
        tfMyMessage.setEditable(false);
        p2.add(lbMyMessage);
        p2.add(tfMyMessage, BorderLayout.SOUTH);

        // Panel p3 - Messages

        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());

        JLabel lbMessages = new JLabel("Messages:");
        taMessages = new JTextArea("", 15, 34);
        taMessages.setEditable(false);
        p3.add(lbMessages);
        p3.add(taMessages, BorderLayout.SOUTH);

        Container cc = getContentPane();
        cc.setLayout(new FlowLayout());
        cc.add(p1);
        cc.add(p2);
        cc.add(p3);
    }

    /** Afhandeling van een actie van het GUI. */
    public void actionPerformed(ActionEvent ev) {
        Object src = ev.getSource();
        if (src == bConnect) {
            System.out.println("Pressed connect!");
        }
    }

    /**
     * Probeert een socket-verbinding op te zetten met de Server.
     * Als alle parametervelden geldig zijn, dan wordt getracht een
     * Client-object te construeren die de daadwerkelijke 
     * socketverbinding met de Server maakt. Als dit gelukt is 
     * wordt een aparte thread (van Client) opgestart, die de 
     * berichten van de Server leest.
     * Daarna worden alle parametervelden en de "Connect" Button
     * niet selecteerbaar gemaakt.
     */
    public void connect() {
        // BODY NOG TOE TE VOEGEN
        addMessage("Connected to server...");
    }

    /** Voegt een bericht toe aan de TextArea op het frame. */
    public void addMessage(String msg) {
        // BODY NOG TOE TE VOEGEN
    }

    /** Start een ClientGUI applicatie op. */
    public static void main(String args[]) {
        ClientGUI gui = new ClientGUI();
    }

} // end of class ClientGUI
