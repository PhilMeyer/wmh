package org.pnm.wmh.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.pnm.wmh.event.ActivationEvent;
import org.pnm.wmh.event.EventManager;
import org.pnm.wmh.event.SelectionEvent;
import org.pnm.wmh.event.SelectionListener;
import org.pnm.wmh.model.Unit;
import org.pnm.wmh.model.Weapon;
import org.pnm.wmh.rw.Game;

public class ButtonPanel extends JPanel implements SelectionListener{

	JButton activate = new JButton("Activate");
	JPanel attacks = new JPanel();
	JPanel spacer = new JPanel();
	
	Unit selected;
	Game game;
	
	public ButtonPanel(Game game){
		EventManager.registerSelectionListener(this);
		setupActivateButton();
		spacer.setPreferredSize(new Dimension(100,100));
		add(spacer);
		setPreferredSize(new Dimension(100,300));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		attacks.setLayout(new BoxLayout(attacks, BoxLayout.Y_AXIS));
		attacks.setBackground(Color.DARK_GRAY);
		this.game = game;
		add(attacks);
		add(spacer);
	}

	private void setupActivateButton() {
		activate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventManager.notify(new ActivationEvent(selected));
			}
		});
		add(activate);
	}
	
	@Override
	public void handle(SelectionEvent e) {
		this.selected = e.u;
		if(game.canActivate(selected) && game.getActive() != e.u){
			activate.setEnabled(true);
		}
		else{
			activate.setEnabled(false);
		}
		attacks.removeAll();
		for(Weapon w : e.u.getWeapons()){
			int pow = w.isRanged() ? w.pow : w.pow + e.u.str;
			JButton attack = new JButton(w.name + " " +pow);
			attack.setPreferredSize(new Dimension(100,50));
			attacks.add(attack);
		}
	}
	
	
}
