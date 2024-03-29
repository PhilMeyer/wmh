package org.pnm.wmh.fact;

import org.pnm.wmh.model.Base;
import org.pnm.wmh.model.MeleeWeapon;
import org.pnm.wmh.model.RangedWeapon;
import org.pnm.wmh.model.Trooper;
import org.pnm.wmh.model.Unit;
import org.pnm.wmh.model.Warcaster;
import org.pnm.wmh.model.Warjack;
import org.pnm.wmh.model.WeaponMod;


public class SearforgeFactory {


	public static Unit gorten() {
		Unit unit = new Warcaster("Gorten Grundback", "M_W_Gorten.png", Base.SMALL, 4, 7, 7, 5, 13, 19, 7, 5);
		unit.addWep(new MeleeWeapon("Forge Father", 5, WeaponMod.CRITICAL_SMITE));
		unit.addWep(new RangedWeapon("Double-Barreled Handcannon", 12, 5, 2, WeaponMod.BOTH_BARRELS));
		return unit;
	}

	public static Unit driller() {
		Unit unit = new Warjack("Driller", "M_J_Driller.png", Base.LARGE, 4, 12, 6, 5, 9, 19);
		unit.addWep(new MeleeWeapon("Grappler", 4, WeaponMod.OPEN_FIST));
		unit.addWep(new MeleeWeapon("Drill", 5));
		return unit;
	}

	public static Unit gunner() {
		Unit unit = new Warjack("Gunner", "M_J_Gunner.png", Base.MEDIUM, 5, 6, 5, 6, 12, 18);
		unit.addWep(new RangedWeapon("Cannon", 12, 12));
		return unit;
	}
	

	public static Unit forgeGuard() {// SPD STR MAT RAT DEF ARM CMD
		Unit unit = new Trooper("Forgeguard", "M_U_ForgeGuard.png", Base.SMALL, 4, 6, 7, 4, 10, 18, 9);
		// ARM includes defensive line
		unit.addWep(new MeleeWeapon("Piston Hammer", 5, WeaponMod.WEAPON_MASTER));
		return unit;
	}

	// public static Unit blaster(){
	// Unit unit = new Unit("M_J_Blaster.png", 40, 6,6,6,6,13,13,7);
	// unit.addWep(new Weapon(3));
	// return unit;
	// }
	//
	// public static Unit thor(){
	// Unit unit = new Unit("M_S_Thor.png", 30, 6,6,6,6,13,13,7);
	// unit.addWep(new Weapon(3));
	// return unit;
	// }
	//

	//
	//
	// public static Unit praetorian(){
	// Unit unit = new Unit("S_U_Praetorian.png", 30,6,6,6,6,13,13,7);
	// unit.addWep(new Weapon(6));
	// return unit;
	// }
	//
}
