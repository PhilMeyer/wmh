package com.ess.util.mw.behave;

import com.ess.util.mw.Unit;
import com.ess.util.mw.rw.Environment;

public interface BehavioralStrategy {

	public Action behave(Unit u, Environment e);
	
}