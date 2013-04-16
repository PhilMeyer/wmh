package com.ess.util.mw.rw;


public class GeometryUtils {

	public static double distance(Location l1, Location l2) {
		return Math.sqrt(Math.pow(l1.x - l2.x, 2) + Math.pow(l1.y - l2.y, 2));
	}

	public static void main(String[] args) {
		System.out.println(calculateNewLocation(new Location(0, 0), new Location(10, 100), 15));
	}

	public static Location calculateNewLocation(Location start, Location goal, double allotment) {
		double totalDistance = distance(start, goal);
		double ratio = allotment / totalDistance;
		if (ratio >= 1) {
			return goal;
		}
		double dx = goal.x - start.x;
		double dy = goal.y - start.y;

		double newX = start.x + ratio * dx;
		double newY = start.y + ratio * dy;
		return new Location(newX, newY, start.direction);
	}

	public static Location calculateNewLocation(Location start, double allotment) {
		double dir = Math.toRadians(start.direction - 90);

		double x = Math.cos(dir) * allotment + start.x;
		double y = Math.sin(dir) * allotment + start.y;

		return new Location(x, y, start.direction);
	}

	public static int getAngle(double x1, double y1, double x2, double y2) {
		double angle = Math.atan2(x2 - x1, y2 - y1) * 180 / Math.PI;
		if (angle < 0) {
			angle = 360 + angle;
		}
		return (int) Math.round(angle);
	}

	public static int getAngle(Location l1, Location l2) {
		int angle = getAngle(l1.x, l1.y, l2.x, l2.y);
		// System.out.println("The angle between "+l1+" and "+l2+" is "+angle);
		return angle;
	}
}
