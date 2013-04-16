package com.ess.util.mw;

import java.util.ArrayList;
import java.util.List;

import com.ess.util.mw.behave.BehavioralStrategy;
import com.ess.util.ui.RunWMH.Side;

public class Unit {

	public final int str, def, arm, rat, mat, spd, cmd;
	int hp = 1;
	public final int base;
	
	List<Ability> abilities = new ArrayList<>();
	List<Weapon> weapons = new ArrayList<>();
	public final String imagePath;
	BehavioralStrategy behavior;
	Side side;

	public Unit(String imagePath, int base, int spd, int str, int mat, int rat, int def, int arm, int cmd){
		this.spd = spd;
		this.str = str;
		this.mat = mat;
		this.rat = rat;
		this.def = def;
		this.arm = arm;
		this.cmd = cmd;
		this.imagePath = imagePath;
		this.base = base;
	}
	
	public int getDefaultWepPow(){
		Weapon weapon = weapons.get(0);
		int pow = 0;
		if(weapon != null){
			pow = weapon.pow;
		}
		return pow;
	}

	public void addWep(Weapon weapon) {
		weapons.add(weapon);
	}
	
	public String toString(){
		return imagePath;
	}
	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public Weapon getDefaultWeapon() {
		return weapons.get(0);
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public int getMaxRange() {
		int max = 0;
		for(Weapon w : weapons){
			if(w.rng > max){
				max = w.rng;
			}
		}
		return max;
	}

	public boolean hasWeaponMod(WeaponMod mod) {
		for(Weapon w : weapons){
			if(w.hasMod(mod)){
				return true;
			}
		}
		return false;
	}
}
