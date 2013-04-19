package org.pnm.wmh.resolve;

import java.text.MessageFormat;

import org.pnm.wmh.fact.CryxFactory;
import org.pnm.wmh.fact.SearforgeFactory;
import org.pnm.wmh.model.Unit;
import org.pnm.wmh.model.WeaponMod;


public class Resolver {

	static boolean print = true;

	EffectTracking effectTracking;

	public Resolver(EffectTracking effectTracking) {
		this.effectTracking = effectTracking;
	}

	public boolean hit(Atk atk) {
		int numDie = atk.atkBoost ? 3 : 2;
		int atkRoll = Die.d6(numDie);
		int totalAtk = atk.a.mat + atkRoll;
		boolean hit = atk.d.def <= totalAtk;
		String hitMissString = (hit) ? "Hit!" : "Miss.";
		log("{0} >> {1}", atk.a, atk.d);
		log("ATK[{1}] vs DEF[{4}]:  {0}({5}d6) + {1} = {2}. {3}", atkRoll, atk.a.mat, totalAtk, hitMissString,
				atk.d.def, numDie);
		return hit;
	}

	public int calculateDamage(Atk atk) {
		StringBuilder damModReport = new StringBuilder();
		int numDie = atk.damBoost ? 3 : 2;
		int damRoll = Die.d6(numDie);
		int ps = atk.wep.pow + atk.a.str;
		damRoll += modifiers(atk, damModReport);
		int effectiveArmor = getEffectiveArmor(atk.d);
		int calcDam = ps + damRoll - effectiveArmor;
		if (calcDam < 0) {
			calcDam = 0;
		}
		log("P+S[{0}] vs ARM[{1}]:  {2}({4}d6) + {0} - {1} {5}= {3}", ps, effectiveArmor, damRoll, calcDam, numDie,
				damModReport);
		return calcDam;
	}

	private int getEffectiveArmor(Unit d) {
		int mod = 0;
		if (effectTracking.hasEffect(d, Debuff.DUSK_SHROUD)) {
			log("DUSK SHROUND reducing armor by 2.");
			mod -= 2;
		}
		return d.arm + mod;
	}

	public int resolve(Atk atk) {
		boolean hit = hit(atk);
		int calcDam = 0;
		if (hit) {
			calcDam = calculateDamage(atk);
		}
		return calcDam;
	}

	private int modifiers(Atk atk, StringBuilder damModReport) {
		int mod = 0;
		// Break each mod into own classes
		if (atk.wep.hasMod(WeaponMod.WEAPON_MASTER)) {
			int roll = Die.d6(1);
			mod += roll;
			damModReport.append(MessageFormat.format("+ {0}(1d6)", roll));
		}

		return mod;
	}

	public static void main(String[] args) {
		Unit s = CryxFactory.slayer();
		Unit d = SearforgeFactory.driller();
		Resolver r = new Resolver(new EffectTracking());
		Atk a1 = new Atk(s, d, s.getDefaultWeapon(), false, true);
		Atk a2 = new Atk(d, s, d.getDefaultWeapon(), true, false);
		r.resolve(a1);
		System.out.println();
		r.resolve(a2);
	}

	public static void log(String format, Object... args) {
		if (print)
			System.out.println(MessageFormat.format(format, args));
	}

}