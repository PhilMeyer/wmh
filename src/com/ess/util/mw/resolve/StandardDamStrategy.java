package com.ess.util.mw.resolve;

import com.ess.util.mw.Unit;
import com.ess.util.mw.Weapon;
import com.ess.util.mw.WeaponMod;

public class StandardDamStrategy implements DamResolveStrategy {

	@Override
	public int resolve(Unit a, Unit d, Weapon wep) {
		int damRoll = Sim.d2.roll();
		int ps = wep.pow + a.str;
		if(wep.hasMod(WeaponMod.WEAPON_MASTER)){
			damRoll += Sim.d1.roll();
		}
		int calcDam = ps + damRoll - d.arm;
		if(calcDam < 0){
			calcDam = 0;
		}
		Sim.log("P+S[{0}] vs ARM[{1}]:  {2}(2d6) + {0} - {1} = {3}", ps, d.arm, damRoll, calcDam);
		return calcDam;
	}

}
