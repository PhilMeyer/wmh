package org.pnm.wmh.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.pnm.wmh.rw.Environment;
import org.pnm.wmh.rw.Game;
import org.pnm.wmh.ui.handlers.KeyHandler;


public class GameFrame extends JFrame{

	private static final long serialVersionUID = -2902674457731927150L;

	Display display;
	InfoBar infoBar;
	UnitBox unitBox = new UnitBox();	
	ButtonPanel buttons;
	
	public GameFrame(Game g){
		super("Totally Not Copyright Violation");
		display = new Display(g, this);
		infoBar = new InfoBar(g);
		assemble(g);
		pack();
		//setLocation(500,100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void assemble(Game g) {
		buttons = new ButtonPanel(g);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		JPanel bottom = new JPanel();
		contentPane.setLayout(new BorderLayout());
		bottom.add(display);
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());
		right.add(buttons, BorderLayout.NORTH);
		right.add(unitBox, BorderLayout.SOUTH);
		bottom.add(right);
		contentPane.add(infoBar, BorderLayout.PAGE_START);
		contentPane.add(bottom, BorderLayout.CENTER);
		setFocusable(true);
		addKeyListener(new KeyHandler(display));
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
