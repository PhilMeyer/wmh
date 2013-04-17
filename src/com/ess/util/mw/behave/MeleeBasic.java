package com.ess.util.mw.behave;

import java.util.List;

import com.ess.util.mw.model.Unit;
import com.ess.util.mw.rw.Environment;

public class MeleeBasic implements BehavioralStrategy{

	@Override
	public Action behave(Unit u, Environment e) {
		List<Unit> enemiesInRange = e.getEnemiesInRange(u,30); // TODO range
		if(!enemiesInRange.isEmpty()){
			return new AttackAction(u,enemiesInRange.get(0));
		}
		List<Unit> nearestEnemies = e.getNearestEnemies(u);
		if(!nearestEnemies.isEmpty()){
			return new MoveTowardsAction(u, e.getLocation(nearestEnemies.get(0)));
		}
		return null; // do nothing
	}

}
