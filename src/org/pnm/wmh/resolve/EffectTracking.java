package org.pnm.wmh.resolve;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.pnm.wmh.model.Unit;


public class EffectTracking {

	Map<Unit, Set<Effect>> activeEffects = new HashMap<>(); 
	
	public void applyEffect(Unit u, Effect e){
		if(!activeEffects.containsKey(u)){
			activeEffects.put(u, new HashSet<Effect>());
		}
		activeEffects.get(u).add(e);
	}

	public boolean hasEffect(Unit u, Effect e){
		Set<Effect> set = activeEffects.get(u);
		return set != null && set.contains(e);
	}
	
}

