package com.ess.util.mw.resolve;

import com.ess.util.mw.Weapon;
import com.ess.util.mw.model.Unit;

public interface DamResolveStrategy {

	int resolve(Unit a, Unit b, Weapon wep);
	
}
