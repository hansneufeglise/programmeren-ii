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
        setResizable(false);

        // Panel p1 - Listen

        JPanel p1 = new JPanel(new FlowLayout());
        JPanel pp1 = new JPanel(new GridLayout(3, 2));

        JLabel lbAddress = new JLabel("Address: ");
        tfAddress = new JTextField("localhost");
        tfAddress.addKeyListener(new KeyHandler());

        JLabel lbPort = new JLabel("Port:");
        tfPort        = new JTextField("2727");
        tfPort.addKeyListener(new KeyHandler());

        JLabel lbName = new JLabel("Name:");
        tfName        = new JTextField(12);
        tfName.addKeyListener(new KeyHandler());

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
        tfMyMessage.addKeyListener(
        		new KeyAdapter() {
    				public void keyReleased(KeyEvent ev) {
    					// Bij enter bericht versturen
    					if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
    						JTextField tx = (JTextField) ev.getSource();
    						if (!tx.getText().equals("")) {
    							client.sendMessage(tx.getText());
    							tx.setText("");
    						}
    					}
    				}
        		}
        		
        );
        p2.add(lbMyMessage);
        p2.add(tfMyMessage, BorderLayout.SOUTH);

        // Panel p3 - Messages

        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());

        JLabel lbMessages = new JLabel("Messages:");
        taMessages = new JTextArea("", 15, 34);
        taMessages.setEditable(false);
        taMessages.setLineWrap(true);
        
        JScrollPane spMessages = new JScrollPane(taMessages);
        spMessages.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        p3.add(lbMessages);
        p3.add(spMessages, BorderLayout.SOUTH);

        Container cc = getContentPane();
        cc.setLayout(new FlowLayout());
        cc.add(p1);
        cc.add(p2);
        cc.add(p3);
    }
    
    private class KeyHandler extends KeyAdapter {
    	public void keyReleased (KeyEvent ev) {
        	if (!tfAddress.getText().equals("") &&
        			!tfPort.getText().equals("") &&
        			!tfName.getText().equals(""))
        	{
        		bConnect.setEnabled(true);
        		
				if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
					actionPerformed(new ActionEvent(bConnect, 0, ""));
				}
        	} else {
        		bConnect.setEnabled(false);
        	}
    	}
    }

    /** Afhandeling van een actie van het GUI. */
    public void actionPerformed(ActionEvent ev) {
        Object src = ev.getSource();
        if (src == bConnect) {
        	bConnect.setEnabled(false);
            connect();
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
    	// Hostname oplossen
    	InetAddress address = null;
        try {
            address = InetAddress.getByName(tfAddress.getText());            
        } catch (UnknownHostException e) {
            addMessage("Host niet gevonden: " + tfAddress.getText());
        }

        if(address != null) {
	        try {
				client = new Client(tfName.getText(), address, Integer.parseInt(tfPort.getText()), this);
	
				new Thread(client).start();

				tfAddress.setEditable(false);
				tfPort.setEditable(false);
				tfName.setEditable(false);

				tfMyMessage.setEditable(true);
			} catch (NumberFormatException e) {
				addMessage(tfPort.getText() + " is geen poortnummer");
			} catch (IOException e) {
				addMessage("Kon geen socket maken op port " + tfPort.getText() + " en adres " + tfAddress.getText());
				bConnect.setEnabled(true);
			}
        }

    }

    /** Voegt een bericht toe aan de TextArea op het frame. */
    public void addMessage(String msg) {
        taMessages.append(msg + "\n");
    }

    /** Start een ClientGUI applicatie op. */
    public static void main(String args[]) {
        ClientGUI gui = new ClientGUI();
    }

} // end of class ClientGUI
