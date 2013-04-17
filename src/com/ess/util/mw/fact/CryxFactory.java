package com.ess.util.mw.fact;

import com.ess.util.mw.Base;
import com.ess.util.mw.MeleeWeapon;
import com.ess.util.mw.SprayWeapon;
import com.ess.util.mw.WeaponMod;
import com.ess.util.mw.model.Unit;
import com.ess.util.mw.model.Warcaster;
import com.ess.util.mw.model.Warjack;

public class CryxFactory {
	
	public static Unit deneghra() {
		Unit unit = new Warcaster("War Witch Deneghra", "X_W_Deneghra_slime.png", Base.SMALL, 7, 5, 5, 4, 16, 14, 8, 7);
		unit.addWep(new MeleeWeapon("Sliver", 5, WeaponMod.SHADOW_BIND));
		return unit;
	}

	public static Unit deathripper() {
		Unit unit = new Warjack("Deathripper", "X_J_Deathripper_slime.png", Base.MEDIUM, 7, 7, 6, 5, 15, 14);
		unit.addWep(new MeleeWeapon("Mandible", 6));
		return unit;
	}

	public static Unit defiler() {
		Unit unit = new Warjack("Defiler", "X_J_Defiler_slime.png", Base.MEDIUM, 7, 7, 6, 5, 15, 14);
		unit.addWep(new SprayWeapon("Sludge Cannon", 8, 12));
		return unit;
	}

	public static Unit slayer() {
		Unit unit = new Warjack("Slayer", "X_J_Slayer_slime.png", Base.LARGE, 6, 10, 7, 5, 13, 17);
		unit.addWep(new MeleeWeapon("Death Claw", 6, WeaponMod.OPEN_FIST));
		unit.addWep(new MeleeWeapon("Death Claw", 6, WeaponMod.OPEN_FIST));
		unit.addWep(new MeleeWeapon("Tusks", 6));
		// combo strike
		return unit;
	}

//	public static Unit baneThrall() {
//		Unit unit = new Unit("Bane Thrall", "X_U_BaneThrall_slime.png", 30, 5, 6, 5, 6, 12, 18, 7);
//		unit.addWep(new RangedWeapon("Cannon", 12, 3));
//		return unit;
//	}

}
