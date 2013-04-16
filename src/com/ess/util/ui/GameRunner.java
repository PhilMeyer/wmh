package com.ess.util.ui;

import java.util.Set;

import org.apache.log4j.Logger;

import com.ess.util.mw.Unit;
import com.ess.util.mw.behave.Action;
import com.ess.util.mw.behave.AttackAction;
import com.ess.util.mw.behave.BehavioralStrategy;
import com.ess.util.mw.behave.MeleeBasic;
import com.ess.util.mw.behave.MoveTowardsAction;
import com.ess.util.mw.resolve.Sim;
import com.ess.util.mw.rw.Environment;
import com.ess.util.mw.rw.GeometryUtils;
import com.ess.util.mw.rw.Location;

public class GameRunner implements Runnable {

	private static final double MOVE_INCREMENTS = 5;

	Logger log = Logger.getLogger(Environment.class);
	private Environment e;

	Unit u;
	BehavioralStrategy strat = new MeleeBasic();
	TurnTracker turnTracker;
	Action currentAction;
	Sim sim = new Sim();
	
	public GameRunner(Environment e) {
		this.e = e;
		//u = e.getRandomUnit();
		turnTracker = new TurnTracker(e);
	}

	public void run() {
		for (;;) {
//			log.info("TURN " + turnTracker.turn);
//			Set<Unit> toMove = e.getUnits(turnTracker.currentSide);
//			for (Unit u : toMove) {
//				Action a = strat.behave(u, e);
//				//log.debug(a);
//				resolve(a);
//				sleep();
//			}
//			sleep();
//			turnTracker.reset();
		}
	}

	private void resolve(Action a) {
		if (a instanceof MoveTowardsAction) {
			MoveTowardsAction mt = (MoveTowardsAction) a;
			Location start = e.getLocation(mt.u);
			Location goal = mt.location;
			double totalDistance = GeometryUtils.distance(start, goal);
			double moveRemaining = mt.u.spd * 8;
			while(totalDistance > 30 && moveRemaining > 0){
				start = e.getLocation(mt.u);
				double travelled = totalDistance / MOVE_INCREMENTS;
				Location newLocation = GeometryUtils.calculateNewLocation(start, goal, travelled);
				e.place(mt.u, newLocation);
				totalDistance = GeometryUtils.distance(newLocation, goal);
				//log.debug("Distance from goal: "+totalDistance);
				moveRemaining -= travelled;
				sleep(); 
			}
		}
		else if(a instanceof AttackAction){
			AttackAction aa = (AttackAction)a;
			System.out.println(aa.u+" attacking "+aa.target);
			int resolution = sim.resolve(aa.u, aa.target, aa.u.getDefaultWeapon());
			if(resolution > 0){
				e.remove(aa.target);
			}
		}
	}

	private static void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
