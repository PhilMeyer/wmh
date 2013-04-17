package com.ess.util.mw.behave;

import com.ess.util.mw.model.Unit;
import com.ess.util.mw.rw.Environment;

public abstract class Action {

	public abstract void resolve(Environment e, Unit u);

}
