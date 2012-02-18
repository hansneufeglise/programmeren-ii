package week3.stem;

import java.awt.*;
import java.awt.event.*;

public class StemFrame extends Frame {
	private final String DEFAULT_CHOICE = "(kies een partij)";
	private final String DEFAULT_LABEL = "Maak uw keuze:";

	private final String[] partijen = {DEFAULT_CHOICE, "Veel Vrijheid en niets Doen", "Partij van de Aardbei", "Speciale Partij", "Doet 67", "Can Do All"};
	
	public StemFrame() {
		setLayout(new GridLayout(0,1));

		final Label l = new Label(DEFAULT_LABEL);

		final Button b = new Button("OK");
		b.setEnabled(false);

		final Choice c = new Choice();
		for (String partij: partijen) {
			c.add(partij);
		}

		c.addItemListener(
			new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (!e.getItem().equals(DEFAULT_CHOICE)) {
						b.setEnabled(true);
						l.setText("Verander keuze of druk op OK");
					}
				}
			}
		);

		b.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						b.setEnabled(false);
						l.setText(DEFAULT_LABEL);
						c.select(0);
					}

				}
		);
		
		this.add(l);
		this.add(c);
		this.add(b);
		
		this.setSize(200, 150);
		this.setTitle("Stemmachine");
		this.setVisible(true);
	}
	
	public static void main(String args[]) {
		new StemFrame().addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				}
		);
	}
}