package org.pnm.wmh.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.pnm.wmh.WMH.Side;
import org.pnm.wmh.event.ActivationEvent;
import org.pnm.wmh.event.EventManager;
import org.pnm.wmh.event.SelectionEvent;
import org.pnm.wmh.rw.Game;


public class InfoBar extends JPanel{

	JLabel label = new JLabel(Side.PLAYER_1.toString());
	JButton swap = new JButton("END TURN");
	
	public InfoBar(final Game game){
		setPreferredSize(new Dimension(700,50));
		add(label);
		add(swap);
		swap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.swapTurn();
				update(game.getCurrentSide());
				EventManager.notify(new SelectionEvent(null));
				EventManager.notify(new ActivationEvent(null));
			}
		});
	}
	
	public void update(Side side){
		label.setText(side.toString());
	}
	
	
}
