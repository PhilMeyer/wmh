package org.pnm.wmh.rw;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.pnm.wmh.model.Unit;


public class MoveTracking {

	Map<Unit,Double> moveRemaining = new HashMap<>();
	
	public void initialize(Collection<Unit> units){
		for(Unit u : units){
			moveRemaining.put(u, (double)u.spd); // TODO debuffs!
		}
	}
	
	public double getRemainingMovement(Unit u){
		return moveRemaining.get(u);
	}
	
	public void decrement(Unit u, double distance){
		Double current = moveRemaining.get(u);
		moveRemaining.put(u, current-distance);
	}
	
	
}
