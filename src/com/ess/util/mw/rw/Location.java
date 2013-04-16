package com.ess.util.mw.rw;

import java.text.MessageFormat;

public class Location {
	public final double x, y;
	public int direction;
	
	public Location(double x, double y, int direction){
		this.x = x;
		this.y = y;
		if(direction < 0){
			direction = 360+direction;
		}
		if(direction > 360){
			direction = direction % 360;
		}
		this.direction = direction;
	}
	
	public Location(double x, double y){
		this(x,y,0);
	}
	
	public String toString(){
		String format = "({0,number,0.0},{1,number,0.0} - {2})";
		return MessageFormat.format(format, x, y, direction);
	}
	
	public static void main(String[] args) {
		System.out.println(new Location(0.0, 14.598));
	}

	public int intX() {
		return Math.round((float)x);
	}

	public int intY() {
		return Math.round((float)y);
	}
	
}
