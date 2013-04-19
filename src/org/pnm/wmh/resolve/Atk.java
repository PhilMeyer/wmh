package org.pnm.wmh.resolve;

import org.pnm.wmh.model.Unit;
import org.pnm.wmh.model.Weapon;


public class Atk {
	public final boolean damBoost, atkBoost;
	public final Unit a, d;
	public final Weapon wep;
	int atkPenalties;
	int damPenalties;

	public Atk(Unit a, Unit d, Weapon wep) {
		this(a, d, wep, false, false);
	}

	public Atk(Unit a, Unit d, Weapon wep, boolean atkBoost, boolean damBoost) {
		this.a = a;
		this.d = d;
		this.damBoost = damBoost;
		this.atkBoost = atkBoost;
		this.wep = wep;
	}
}
