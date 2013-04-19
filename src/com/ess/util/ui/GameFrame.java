package com.ess.util.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ess.util.mw.rw.Environment;

public class GameFrame extends JFrame{

	private static final long serialVersionUID = -2902674457731927150L;

	Display display;
	InfoBar infoBar;
	UnitBox unitBox = new UnitBox();	
	
	public GameFrame(Environment e){
		super("Hate this part");
		display = new Display(e, this);
		infoBar = new InfoBar(e);
		assemble(e);
		pack();
		//setLocation(500,100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void assemble(Environment e) {
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		JPanel bottom = new JPanel();
		contentPane.setLayout(new BorderLayout());
		bottom.add(display);
		bottom.add(unitBox);
		contentPane.add(infoBar, BorderLayout.PAGE_START);
		contentPane.add(bottom, BorderLayout.CENTER);
		//JPanel right = new JPanel();
		//right.add(unitBox);
		//unitBox.add(right);
	}

	public void refreshDisplay() {
		display.repaint();
	}

	public UnitBox getUnitBox() {
		return unitBox;
	}

	public void setUnitBox(UnitBox unitBox) {
		this.unitBox = unitBox;
	}
	
	
}
