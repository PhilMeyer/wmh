package org.pnm.wmh.model;

public class SprayWeapon extends Weapon{

	public SprayWeapon(String name, int rng, int pow, WeaponMod... mods) {
		this(name, rng, pow, 1, mods);
	}

	public SprayWeapon(String name, int rng, int pow, int rof, WeaponMod... mods) {
		super(name, rng, pow, rof, mods);
	}

}
