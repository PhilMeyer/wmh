package org.pnm.wmh.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.pnm.wmh.WMH.Side;
import org.pnm.wmh.rw.Environment;


public class InfoBar extends JPanel{

	JLabel label = new JLabel(Side.PLAYER_1.toString());
	JButton swap = new JButton("END TURN");
	
	public InfoBar(final Environment environment){
		setPreferredSize(new Dimension(700,50));
		add(label);
		add(swap);
		swap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				environment.swapTurn();
				update(environment.getCurrentSide());
			}
		});
	}
	
	public void update(Side side){
		label.setText(side.toString());
	}
	
	
}
