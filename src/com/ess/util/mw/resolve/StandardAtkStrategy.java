package com.ess.util.mw.resolve;

import com.ess.util.mw.model.Unit;


public class StandardAtkStrategy implements AtkResolveStrategy{

	
	@Override
	public boolean hit(Unit a, Unit d) {
		int atkRoll = Sim.d2.roll();
		int totalAtk = a.mat + atkRoll;
		boolean hit = d.def <= totalAtk;
		String hitMissString = (hit) ? "Hit!" : "Miss.";
		Sim.log("ATK[{1}] vs DEF[{4}]:  {0}(2d6) + {1} = {2}. {3}", atkRoll, a.mat, totalAtk, hitMissString, d.def);
		return hit;
	}

}
