package com.ess.util.mw.resolve;

import com.ess.util.mw.Unit;
import com.ess.util.mw.Weapon;

public interface DamResolveStrategy {

	int resolve(Unit a, Unit b, Weapon wep);
	
}
