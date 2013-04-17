package com.ess.util.mw.resolve;

import com.ess.util.mw.Weapon;
import com.ess.util.mw.model.Unit;

public class PenDamStrategy implements DamResolveStrategy {

	@Override
	public int resolve(Unit a, Unit b, Weapon wep) {
		return 1;
	}

}
