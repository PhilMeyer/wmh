package com.ess.util.mw.behave;

import com.ess.util.mw.model.Unit;
import com.ess.util.mw.rw.Environment;

public class AttackAction extends Action {

	public final Unit u;
	public final Unit target;
	
	public AttackAction(Unit u, Unit target) {
		this.u = u;
		this.target = target;
	}

	@Override
	public void resolve(Environment e, Unit u) {
		
	}

}
