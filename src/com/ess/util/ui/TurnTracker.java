package com.ess.util.ui;

import java.util.HashMap;
import java.util.Map;

import com.ess.util.mw.Unit;
import com.ess.util.mw.rw.Environment;
import com.ess.util.ui.RunWMH.Side;

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
