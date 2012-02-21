package week3.bke;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.*;

public class BKEView extends JFrame {
	JPanel p; // panel for buttons
	List<JButton> lb;
	JLabel l;
	JButton b;
	
	public BKEView() {
		super("Boter, kaas en eieren");
		
		Container cp = this.getContentPane();
		
		p = new JPanel();
		lb = new ArrayList<JButton>();
		for(int i = 0; i < Bord.DIM; i++) {
			for(int j = 0; j < Bord.DIM; j++) {
				JButton b = new JButton();

				p.add(b);
				lb.add(b);
			}
		}
		
		p.setLayout(new GridLayout(3,3));
		cp.add(p);

		JPanel controlPanel = new JPanel();

		// spacer
		controlPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		l = new JLabel("Er is iemand aan de beurt.");
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		controlPanel.add(l);

		// spacer
		controlPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		b = new JButton("Nog een keer?");
		b.setEnabled(false);
		b.setAlignmentX(Component.CENTER_ALIGNMENT);
		controlPanel.add(b);

		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
		cp.add(controlPanel);

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setSize(200, 200);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new BKEView();
	}
}
