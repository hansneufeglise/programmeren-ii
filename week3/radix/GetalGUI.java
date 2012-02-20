package week3.radix;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GetalGUI extends JFrame implements Observer {
	private JLabel l;
	private Getal getal;
	private JTextField getalField, radixField;

	public GetalGUI() {
		super("Getalconverteerder");
		this.setLayout(new GridLayout(3,2));
		
		getal = new Getal();
		
		getal.addObserver(this);

		l = new JLabel("Huidige waarde: " + getal.getWaarde());

		getalField = new JTextField();
		getalField.addKeyListener(
			new KeyAdapter() {
				public void keyReleased(KeyEvent ev) {
					if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							getal.setWaarde(Integer.parseInt(getalField.getText()));
						} catch (NumberFormatException e) {
							l.setText("Error: " + getalField.getText() + " is geen getal");
			            } catch (IllegalArgumentException e) {
			            	l.setText(e.getMessage());
			        	}

					}
				}
			}
		);
		radixField = new JTextField();
		radixField.addKeyListener(
			new KeyAdapter() {
				public void keyReleased(KeyEvent ev) {
					if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							new GetalObserver(getal, Integer.parseInt(radixField.getText()));
						} catch (NumberFormatException e) {
			        		l.setText(e.getMessage());
			        	} catch (IllegalArgumentException e) {
			        		l.setText(e.getMessage());
			        	}

					}
				}
			}
		);

		this.add(new JLabel("Nieuwe waarde:"));
		this.add(getalField);

		this.add(new JLabel("Radix nieuwe observer:"));
		this.add(radixField);

		this.add(l);

		this.setSize(400, 150);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GetalGUI();
	}

	@Override
	public void update(Observable o, Object arg) {
		l.setText("Huidige waarde: " + arg);
	}
}