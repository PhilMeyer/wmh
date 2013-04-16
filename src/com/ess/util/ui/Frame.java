package com.ess.util.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ess.util.mw.rw.Environment;

public class Frame extends JFrame{

	Display display;
	
	UnitBox unitBox = new UnitBox();	
	
	public Frame(Environment e){
		super("Hate this part");
		display = new Display(e, unitBox);
		assemble(e);
		pack();
		//setLocation(500,100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void assemble(Environment e) {
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.add(display);
		contentPane.add(unitBox);  
		//JPanel right = new JPanel();
		//right.add(unitBox);
		//unitBox.add(right);
	}

	public void refreshDisplay() {
		display.repaint();
	}
	
	
}
