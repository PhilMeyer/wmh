package org.pnm.wmh;

import org.pnm.wmh.fact.CryxFactory;
import org.pnm.wmh.fact.SearforgeFactory;
import org.pnm.wmh.rw.Environment;
import org.pnm.wmh.rw.Game;
import org.pnm.wmh.ui.GameFrame;


public class WMH {

	public static enum Side {
		PLAYER_1, PLAYER_2
	};

	public static void main(String[] args) {
		Game g = setupGame();
		showUI(g);
	}

	protected static void showUI(Game g) {
		GameFrame frame = new GameFrame(g);
	}

	private static Game setupGame() {
		Game g = new Game();
		Environment e = new Environment(g);
		g.setEnvironment(e);

		e.addUnit(Side.PLAYER_1, CryxFactory.deneghra(), 360, 150, 180);
		e.addUnit(Side.PLAYER_1, CryxFactory.deathripper(), 410, 100, 180);
		e.addUnit(Side.PLAYER_1, CryxFactory.deathripper(), 300, 100, 180);
		e.addUnit(Side.PLAYER_1, CryxFactory.defiler(), 200, 170, 180);
		e.addUnit(Side.PLAYER_1, CryxFactory.slayer(), 480, 150, 180);

		e.addUnit(Side.PLAYER_2, SearforgeFactory.gorten(), 360, 560);
		e.addUnit(Side.PLAYER_2, SearforgeFactory.driller(), 410, 500);
		e.addUnit(Side.PLAYER_2, SearforgeFactory.gunner(), 480, 550);
		e.addUnit(Side.PLAYER_2, SearforgeFactory.gunner(), 320, 500);
		e.addUnit(Side.PLAYER_2, SearforgeFactory.gunner(), 250, 550);
		g.swapTurn();
		return g;
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
