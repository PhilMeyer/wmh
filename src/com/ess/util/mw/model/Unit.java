package com.ess.util.mw.model;

import java.util.ArrayList;
import java.util.List;

import com.ess.util.mw.Ability;
import com.ess.util.mw.Base;
import com.ess.util.mw.Weapon;
import com.ess.util.mw.WeaponMod;
import com.ess.util.ui.RunWMH.Side;

public abstract class Unit {

	static int id = 0;
	
	public final int str, def, arm, rat, mat, spd, cmd;
	int hp = 1;
	public final Base base;
	
	List<Ability> abilities = new ArrayList<>();
	List<Weapon> weapons = new ArrayList<>();
	public final String imagePath;
	Side side;
	public final String name;

	public Unit(String name, String imagePath, Base base, int spd, int str, int mat, int rat, int def, int arm, int cmd){
		this.name = name;
		this.spd = spd;
		this.str = str;
		this.mat = mat;
		this.rat = rat;
		this.def = def;
		this.arm = arm;
		this.cmd = cmd;
		this.imagePath = imagePath;
		this.base = base;
		id++;
	}

	public void addWep(Weapon weapon) {
		weapons.add(weapon);
	}
	
	public String toString(){
		return name+"_"+id;
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
