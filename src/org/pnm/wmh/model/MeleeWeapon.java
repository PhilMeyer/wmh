package org.pnm.wmh.model;

public class MeleeWeapon extends Weapon{

	public MeleeWeapon(String name, int pow, WeaponMod... mods) {
		super(name, -1, pow, 1, mods);
	}

}
