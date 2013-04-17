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

		e.addUnit(Side.PLAYER_1, UnitFactory.deneghra(), 360, 560);
		e.addUnit(Side.PLAYER_1, UnitFactory.deathripper(),410, 500);
		e.addUnit(Side.PLAYER_1, UnitFactory.deathripper(),300, 500);
		e.addUnit(Side.PLAYER_1, UnitFactory.slayer(), 480, 550);
		e.addUnit(Side.PLAYER_2, UnitFactory.baneThrall(), 480, 450);
		e.addUnit(Side.PLAYER_2, UnitFactory.baneThrall(), 480+50, 450);
		e.addUnit(Side.PLAYER_2, UnitFactory.baneThrall(), 480+50*2, 450);
		e.addUnit(Side.PLAYER_2, UnitFactory.baneThrall(), 480+50*3, 450);
		e.addUnit(Side.PLAYER_2, UnitFactory.baneThrall(), 480+50*4, 450);
		e.addUnit(Side.PLAYER_2, UnitFactory.baneThrall(), 480+50*5, 450);
		e.addUnit(Side.PLAYER_2, UnitFactory.baneThrall(), 480+50*6, 450);
		
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 80+200, 80, 180);
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 130+200, 80, 180);
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 180+200, 80, 180);
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 230+200, 80, 180);
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 280+200, 80, 180);
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 80+215, 40, 180);
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 130+215, 40, 180);
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 180+215, 40, 180);
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 230+215, 40, 180);
		e.addUnit(Side.PLAYER_2, UnitFactory.nihilator(), 280+215, 40, 180);
		return e;
	}

}
