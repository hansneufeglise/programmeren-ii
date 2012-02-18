package week3.radix;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GetalObserver extends JFrame implements Observer {
	private JLabel l = new JLabel();
	private Getal getal;
	private int radix;
	

	public GetalObserver(Getal g, int r) {
		if (r > Character.MAX_RADIX) {
			throw new NumberFormatException();
		}
		getal = g;
		radix = r;

		getal.addObserver(this);

		setLayout(new GridLayout(0,1));

		this.add(l);

		this.setSize(200, 100);
		this.setTitle("Getalconverteerder");
		this.setVisible(true);

		// set initial value
		update(null, g.getWaarde());
	}


	@Override
	public void update(Observable o, Object obj) {
		String label = obj.toString() + " is in radix " + radix + ": ";
		label += NaarRadix.naarRadix(Integer.parseInt(obj.toString()), radix);
		
		l.setText(label);
	}
}