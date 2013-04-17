package com.ess.util.ui;

import com.ess.util.mw.fact.CryxFactory;
import com.ess.util.mw.fact.SearforgeFactory;
import com.ess.util.mw.rw.Environment;

public class RunWMH {

	public static enum Side {
		PLAYER_1, PLAYER_2
	};

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

		e.addUnit(Side.PLAYER_1, CryxFactory.deneghra(), 360, 150, 180);
		e.addUnit(Side.PLAYER_1, CryxFactory.deathripper(), 410, 100, 180);
		e.addUnit(Side.PLAYER_1, CryxFactory.deathripper(), 300, 100, 180);
		e.addUnit(Side.PLAYER_1, CryxFactory.defiler(), 200, 170, 180);
		e.addUnit(Side.PLAYER_1, CryxFactory.slayer(), 480, 150, 180);

		e.addUnit(Side.PLAYER_1, SearforgeFactory.gorten(), 360, 560);
		e.addUnit(Side.PLAYER_1, SearforgeFactory.driller(), 410, 500);
		e.addUnit(Side.PLAYER_1, SearforgeFactory.gunner(), 480, 550);
		e.addUnit(Side.PLAYER_1, SearforgeFactory.gunner(), 320, 500);
		e.addUnit(Side.PLAYER_1, SearforgeFactory.gunner(), 250, 550);

		return e;
	}

//	protected static void addBanes(Environment e) {
//		e.addUnit(Side.PLAYER_2, CryxFactory.baneThrall(), 480, 450);
//		e.addUnit(Side.PLAYER_2, CryxFactory.baneThrall(), 480 + 50, 450);
//		e.addUnit(Side.PLAYER_2, CryxFactory.baneThrall(), 480 + 50 * 2, 450);
//		e.addUnit(Side.PLAYER_2, CryxFactory.baneThrall(), 480 + 50 * 3, 450);
//		e.addUnit(Side.PLAYER_2, CryxFactory.baneThrall(), 480 + 50 * 4, 450);
//		e.addUnit(Side.PLAYER_2, CryxFactory.baneThrall(), 480 + 50 * 5, 450);
//		e.addUnit(Side.PLAYER_2, CryxFactory.baneThrall(), 480 + 50 * 6, 450);
//	}

//	protected static void addNih(Environment e) {
//		e.addUnit(Side.PLAYER_2, SearforgeFactory.nihilator(), 80 + 200, 80, 180);
//		e.addUnit(Side.PLAYER_2, SearforgeFactory.nihilator(), 130 + 200, 80, 180);
//		e.addUnit(Side.PLAYER_2, SearforgeFactory.nihilator(), 180 + 200, 80, 180);
//		e.addUnit(Side.PLAYER_2, SearforgeFactory.nihilator(), 230 + 200, 80, 180);
//		e.addUnit(Side.PLAYER_2, SearforgeFactory.nihilator(), 280 + 200, 80, 180);
//		e.addUnit(Side.PLAYER_2, SearforgeFactory.nihilator(), 80 + 215, 40, 180);
//		e.addUnit(Side.PLAYER_2, SearforgeFactory.nihilator(), 130 + 215, 40, 180);
//		e.addUnit(Side.PLAYER_2, SearforgeFactory.nihilator(), 180 + 215, 40, 180);
//		e.addUnit(Side.PLAYER_2, SearforgeFactory.nihilator(), 230 + 215, 40, 180);
//		e.addUnit(Side.PLAYER_2, SearforgeFactory.nihilator(), 280 + 215, 40, 180);
//	}

}
