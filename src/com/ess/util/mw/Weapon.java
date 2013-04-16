package com.ess.util.mw;

public abstract class Weapon {
	public final int pow;
	WeaponMod[] weaponMods;
	public final String name;
	public final int rng, rof;

	public Weapon(String name, int rng, int pow, int rof, WeaponMod... mods) {
		this.pow = pow;
		this.weaponMods = mods;		
		this.name = name;
		this.rng = rng;
		this.rof = rof;
	}
	
	public boolean hasMod(WeaponMod test){
		for(WeaponMod mod : weaponMods){
			if(mod == test){
				return true;
			}
		}
		return false;
	}

	public boolean isRanged() {
		return rng > 0;
	}

	public WeaponMod[] getWeaponMods() {
		return weaponMods;
	}

}