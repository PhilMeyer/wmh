package org.pnm.wmh.rw;

import java.awt.MultipleGradientPaint.CycleMethod;
import java.util.ArrayList;
import java.util.List;

import org.pnm.wmh.WMH.Side;
import org.pnm.wmh.event.ActivationEvent;
import org.pnm.wmh.event.ActivationListener;
import org.pnm.wmh.event.EventManager;
import org.pnm.wmh.model.Unit;

public class Game implements ActivationListener{
	

	Side currentSide = Side.PLAYER_1;
	Environment environment;
	MoveTracking tracking = new MoveTracking();
	Unit active;
	List<Unit> alreadyActivated = new ArrayList<>();
	
	public Game(){
		EventManager.registerActivationListener(this);
	}
	
	public Side getCurrentSide(){
		return currentSide;
	}
	
	public void swapTurn(){
		currentSide = currentSide == Side.PLAYER_1 ? Side.PLAYER_2 : Side.PLAYER_1;
		tracking.initialize(environment.getUnits());
		alreadyActivated.clear();
	}
	
	public double getRemainingMove(Unit u){
		double remaining = tracking.getRemainingMovement(u);
		return remaining;
	}

	public void decrementMove(Unit u, double d) {
		tracking.decrement(u, d);
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public Unit getActive() {
		return active;
	}

	@Override
	public void handle(ActivationEvent e) {
		this.active = e.u;
		alreadyActivated.add(e.u);
		System.out.println(active+" is now active.");
	}

	public boolean canActivate(Unit u) {
		boolean alreadyWent = alreadyActivated.contains(u);
		return u != null && !alreadyWent && u.getSide() == currentSide;
	}
}
