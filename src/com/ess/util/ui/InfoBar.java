package com.ess.util.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ess.util.mw.rw.Environment;
import com.ess.util.ui.RunWMH.Side;

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
