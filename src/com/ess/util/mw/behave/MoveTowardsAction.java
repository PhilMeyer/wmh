package com.ess.util.mw.behave;

import com.ess.util.mw.model.Unit;
import com.ess.util.mw.rw.Environment;
import com.ess.util.mw.rw.Location;

public class MoveTowardsAction extends Action {

	public final Unit u;
	public final Location location;
	
	public MoveTowardsAction(Unit u, Location location) {
		this.u = u;
		this.location = location;
	}
	
	public String toString(){
		return u.toString()+" >> "+location;
	}

	@Override
	public void resolve(Environment e, Unit u) {
		//TurnTracker
	}

}
