package org.pnm.wmh.model;

public class RangedWeapon extends Weapon{

	public RangedWeapon(String name, int rng, int pow, WeaponMod... mods) {
		this(name, rng, pow, 1, mods);
	}

	public RangedWeapon(String name, int rng, int pow, int rof, WeaponMod... mods) {
		super(name, rng, pow, rof, mods);
	}

}
