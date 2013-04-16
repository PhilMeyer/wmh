package com.ess.util.mw;

public class UnitFactory {


//	public static Unit forgeGuard(){//                 SPD STR MAT RAT  DEF  ARM  CMD
//		Unit unit = new Unit("M_U_ForgeGuard.png", 30, 4,  6,   7,  4,  10,  18,  9);
//		// ARM includes defensive line
//		unit.addWep(new MeleeWeapon("Forge Father",5,WeaponMod.WEAPON_MASTER));
//		unit.addWep(new RangedWeapon("Double-Barreled Handcannon",12,5));
//		return unit;
//	}
	
	public static Unit gorten(){
		Unit unit = new Unit("M_W_Gorten.png", 30,4,7,7,5,13,19,7);
		unit.addWep(new MeleeWeapon("Forge Father",5,WeaponMod.CRITICAL_SMITE));
		unit.addWep(new RangedWeapon("Double-Barreled Handcannon",12,5,2,WeaponMod.BOTH_BARRELS));
		return unit;
	}

	public static Unit driller(){
		Unit unit = new Unit("M_J_Driller.png", 50, 4,12,6,5,9,19,7);
		unit.addWep(new MeleeWeapon("Grappler", 4, WeaponMod.OPEN_FIST));
		unit.addWep(new MeleeWeapon("Drill", 5));
		return unit;
	}

	public static Unit gunner(){
		Unit unit = new Unit("M_J_Gunner.png", 40, 5,6,5,6,12,18,7);
		unit.addWep(new RangedWeapon("Cannon",12,3));
		return unit;
	}

//	public static Unit blaster(){
//		Unit unit = new Unit("M_J_Blaster.png", 40, 6,6,6,6,13,13,7);
//		unit.addWep(new Weapon(3));
//		return unit;
//	}
//
//	public static Unit thor(){
//		Unit unit = new Unit("M_S_Thor.png", 30, 6,6,6,6,13,13,7);
//		unit.addWep(new Weapon(3));
//		return unit;
//	}
//

//
//
//	public static Unit praetorian(){
//		Unit unit = new Unit("S_U_Praetorian.png", 30,6,6,6,6,13,13,7);
//		unit.addWep(new Weapon(6));
//		return unit;
//	}
//
	public static Unit nihilator(){
		Unit unit = new Unit("S_U_Nihilator.png", 30,6,6,6,6,14,12,7);
		unit.addWep(new MeleeWeapon("Greatsword",12, WeaponMod.REACH));
		return unit;
	}
}
