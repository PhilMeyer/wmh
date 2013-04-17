package com.ess.util.mw.rw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ess.util.mw.model.Unit;
import com.ess.util.ui.RunWMH.Side;

public class Environment {

	Logger log = Logger.getLogger(Environment.class);
	Map<Unit, Location> unitLocations = new HashMap<>();

	public void addUnit(Side side, Unit u, double x, double y) {
		addUnit(side, u, new Location(x, y, 0));
	}

	public boolean place(Unit u, Location location) {
		Unit unitAt = getCollision(u, location);
		boolean valid = unitAt == null || unitAt.equals(u);
		if(valid){
			unitLocations.put(u, location);
		}
		return valid;
	}
	
	public Unit getCollision(Unit u, Location location){
		for (Entry<Unit, Location> entry : units().entrySet()) {
			double distance = GeometryUtils.distance(new Location(location.x, location.y), entry.getValue());
			double r1 = entry.getKey().base.scaled() / 2;
			double r2 = u.base.scaled() / 2;
			if (distance < r1 + r2 && !entry.getKey().equals(u)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public void addUnit(Side side, Unit u, Location location) {
		u.setSide(side);
		unitLocations.put(u, location);
	}

	public Map<Unit, Location> units() {
		return unitLocations; // TODO defensive copy
	}

	public Unit getUnitAt(double x, double y) {
		for (Entry<Unit, Location> entry : units().entrySet()) {
			double distance = GeometryUtils.distance(new Location(x, y), entry.getValue());
			//log.debug(distance);
			if (distance < entry.getKey().base.size / 2) {
				return entry.getKey();
			}
		}
		return null;
	}

	public Location getLocation(Unit selected) {
		return unitLocations.get(selected);
	}

	public void moveRandomly() {
		for (Entry<Unit, Location> entry : units().entrySet()) {
			move(entry.getKey(), entry.getValue(), 1.0);
		}
	}

	private void move(Unit u, Location l, double d) {
		if (new Random().nextBoolean()) {
			if (new Random().nextBoolean()) {
				unitLocations.put(u, new Location(l.x + d, l.y, l.direction));
			} else {
				unitLocations.put(u, new Location(l.x, l.y + d, l.direction));
			}
		} else {
			if (new Random().nextBoolean()) {
				unitLocations.put(u, new Location(l.x - d, l.y, l.direction));
			} else {
				unitLocations.put(u, new Location(l.x, l.y - d, l.direction));
			}
		}
		if (new Random().nextBoolean()) {
			if (new Random().nextBoolean()) {
				unitLocations.put(u, new Location(l.x, l.y, l.direction + 5));
			}
			else{
				unitLocations.put(u, new Location(l.x, l.y, l.direction - 5));
			}
		}
	}

	public void addUnit(Side side, Unit u, double x, double y, int dir) {
		addUnit(side, u, new Location(x, y, dir));
	}

	public List<Unit> getEnemiesInRange(Unit u, int range) {
		List<UnitDistance> closestUnits = getClosestUnits(u);
		List<Unit> enemiesInRange = new ArrayList<>();
		for(UnitDistance ud : closestUnits){
			if(ud.u.getSide() != u.getSide()){
				if(ud.d < range){
					enemiesInRange.add(ud.u);
				}
			}
		}
		return enemiesInRange;
	}

	public List<Unit> getNearestEnemies(Unit u) {
		List<Unit> enemiesInRange = new ArrayList<>();
		List<UnitDistance> closestUnits = getClosestUnits(u);
		for(UnitDistance ud : closestUnits){
			if(ud.u.getSide() != u.getSide()){
				enemiesInRange.add(ud.u);
			}
		}
		return enemiesInRange;
	}

	public Unit getRandomUnit() {
		// TODO Auto-generated method stub
		return unitLocations.keySet().iterator().next();
	}

	public void moveRandomly(Unit u) {
		move(u, unitLocations.get(u), 1.0);
		
	}
	
	
	
	
	public List<UnitDistance> getClosestUnits(Unit u1) {
		Location l = unitLocations.get(u1);
		List<UnitDistance> list = new ArrayList<>();
		for (Entry<Unit, Location> entry : unitLocations.entrySet()) {
			if(entry.getKey().equals(u1)){
				continue;
			}
			double d = GeometryUtils.distance(l, entry.getValue());
			list.add(new UnitDistance(entry.getKey(), d));
		}
		Collections.sort(list);
		//System.out.println(list);
		return list;
	}

	class UnitDistance implements Comparable<UnitDistance>{
		Unit u;
		double d;

		public UnitDistance(Unit u, double d) {
			this.u = u;
			this.d = d;
		}

		@Override
		public int compareTo(UnitDistance o) {
			return Double.valueOf(d).compareTo(o.d);
		}
		
		public String toString(){
			return u+" - "+d;
		}
	}

	public Set<Unit> getUnits() {
		return unitLocations.keySet();
	}

	public Set<Unit> getUnits(Side currentSide) {
		Set<Unit> side = new HashSet<>();
		for(Unit u : getUnits()){
			if(u.getSide() == currentSide){
				side.add(u);
			}
		}
		return side;
	}

	public void remove(Unit u) {
		unitLocations.remove(u);
	}

	public Map<Unit, Location> getUnitEntries() {
		Map<Unit, Location> copy = new HashMap<>();
		copy.putAll(unitLocations);  
        return copy;
	}

	public Unit getUnitAt(Location loc) {
		return getUnitAt(loc.x, loc.y);
	}


}
