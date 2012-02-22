package week3.stem;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class StemFrame extends Frame implements Observer {
	private final String DEFAULT_CHOICE = "(kies een partij)";
	private final String DEFAULT_LABEL = "Maak uw keuze:";

	private final String[] partijen = {DEFAULT_CHOICE, "Veel Vrijheid en niets Doen", "Partij van de Aardbei", "Speciale Partij", "Doet 67", "Can Do All"};

	Label l;
	Button b;
	Choice c;

	public StemFrame(Uitslag uitslag) {
		super("Stemmachine");
		
		uitslag.addObserver(this);
				
		setLayout(new GridLayout(0,1));

		l = new Label(DEFAULT_LABEL);

		b = new Button("OK");
		b.setEnabled(false);

		c = new Choice();

	  for (String partij : partijen) {
	    if (!partij.equals(DEFAULT_CHOICE)) {
			  c.add(partij);
			  uitslag.voegPartijToe(partij);
		  }
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
		
		final Uitslag u = uitslag; // *cough cough*

		b.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				  // Stem uitvoeren
					u.stem(c.getSelectedItem());
					b.setEnabled(false);
					l.setText(DEFAULT_LABEL);
					c.select(0);
				}
			}
		);
		
		this.addWindowListener(
		  new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				  System.exit(0);
				}
			}
		);
		
		this.add(l);
		this.add(c);
		this.add(b);
		
		this.update(uitslag, null);
		
		this.setSize(200, 150);
		this.setVisible(true);
	}
	
	public void update(Observable obj, Object arg) {	  
	  Uitslag uitslag = (Uitslag)obj;
	  c.removeAll();
	  
	  // Nieuwe partijen toevoegen
	  for (String partij : uitslag.getPartijen()) {
      c.add(partij);	    
	  }
	}

}