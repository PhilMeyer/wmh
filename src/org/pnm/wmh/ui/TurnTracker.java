package org.pnm.wmh.ui;

import java.util.HashMap;
import java.util.Map;

import org.pnm.wmh.WMH.Side;
import org.pnm.wmh.model.Unit;
import org.pnm.wmh.rw.Environment;


public class TurnTracker {

	int turn = 1;
	private Environment e;
	
	Map<Unit, Double> moveRemaining = new HashMap<>();
	Side currentSide = Side.PLAYER_1;
	
	
	public TurnTracker(Environment e){
		this.e = e;
		reset();
	}
	
	public void reset(){
		for(Unit u: e.getUnits()){
			moveRemaining.put(u, Double.valueOf(u.spd));
		}
		turn++;
		currentSide = Side.values()[turn%2];
	}
	
	
}
