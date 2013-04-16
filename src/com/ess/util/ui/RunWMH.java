package com.ess.util.ui;

import com.ess.util.mw.UnitFactory;
import com.ess.util.mw.rw.Environment;

public class RunWMH {

	public static enum Side{PLAYER_1, PLAYER_2};
	
	public static void main(String[] args) {
		Environment e = setupEnvironment();
		showUI(e);
		Thread gameThread = new Thread(new GameRunner(e));
		gameThread.start();
	}

	protected static void showUI(Environment e) {
		Frame frame = new Frame(e);
		Thread refreshThread = new Thread(new DisplayRunner(frame));
		refreshThread.start();
	}

	private static Environment setupEnvironment() {
		Environment e = new Environment();

		//e.addUnit(Side.PLAYER_1, UnitFactory.thor(), 100, 330);
		//e.addUnit(Side.PLAYER_1, UnitFactory.gunner(), 40, 300);

//		int startFG = 140;
//		e.addUnit(Side.PLAYER_1, UnitFactory.forgeGuard(), startFG, 300);
//		e.addUnit(Side.PLAYER_1, UnitFactory.forgeGuard(), startFG+60, 300);
//		e.addUnit(Side.PLAYER_1, UnitFactory.forgeGuard(), startFG+120, 300);
//		e.addUnit(Side.PLAYER_1, UnitFactory.forgeGuard(), startFG+30, 330);
//		e.addUnit(Side.PLAYER_1, UnitFactory.forgeGuard(), startFG+90, 330);
//		e.addUnit(Side.PLAYER_1, UnitFactory.forgeGuard(), startFG+60, 360);
//
//		startFG = 340;
//		e.addUnit(Side.PLAYER_1, UnitFactory.forgeGuard(), startFG, 300);
//		e.addUnit(Side.PLAYER_1, UnitFactory.forgeGuard(), startFG+60, 300);
//		e.addUnit(Side.PLAYER_1, UnitFactory.forgeGuard(), startFG+120, 300);
//		e.addUnit(Side.PLAYER_1, UnitFactory.forgeGuard(), startFG+30, 330);
//		e.addUnit(Side.PLAYER_1, UnitFactory.forgeGuard(), startFG+90, 330);
//		e.addUnit(Side.PLAYER_1, UnitFactory.forgeGuard(), startFG+60, 360);
		e.addUnit(Side.PLAYER_1, UnitFactory.gorten(), 360, 560);
		e.addUnit(Side.PLAYER_1, UnitFactory.driller(),410, 500);
		e.addUnit(Side.PLAYER_1, UnitFactory.gunner(), 480, 550);
		e.addUnit(Side.PLAYER_1, UnitFactory.gunner(), 320, 500);
		e.addUnit(Side.PLAYER_1, UnitFactory.gunner(), 250, 550);
		//e.addUnit(Side.PLAYER_1, UnitFactory.blaster(), 320, 300);
		
//		e.addUnit(Side.PLAYER_2, UnitFactory.praetorian(), 80, 80, 180);
//		e.addUnit(Side.PLAYER_2, UnitFactory.praetorian(), 120, 80, 180);
//		e.addUnit(Side.PLAYER_2, UnitFactory.praetorian(), 160, 80, 180);
//		e.addUnit(Side.PLAYER_2, UnitFactory.praetorian(), 80, 40, 180);
//		e.addUnit(Side.PLAYER_2, UnitFactory.praetorian(), 120, 40, 180);
//		e.addUnit(Side.PLAYER_2, UnitFactory.praetorian(), 160, 40, 180);
//		
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 80+200, 80, 180);
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 120+200, 80, 180);
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 160+200, 80, 180);
//		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 80+200, 40, 180);
//		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 120+200, 40, 180);
//		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 160+200, 40, 180);
		return e;
	}

}
