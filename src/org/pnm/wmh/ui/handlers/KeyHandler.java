package org.pnm.wmh.ui.handlers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.pnm.wmh.Constants;
import org.pnm.wmh.model.Unit;
import org.pnm.wmh.rw.Environment;
import org.pnm.wmh.rw.GeometryUtils;
import org.pnm.wmh.rw.Location;
import org.pnm.wmh.ui.Display;


public class KeyHandler extends KeyAdapter {

	private Display display;

	public KeyHandler(Display display) {
		this.display = display;
		System.out.println("Bleh");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e);
		Unit u = display.getSelected();
		if (u == null) {
			return;
		}
		int degrees;
		double distance;
		Location current = display.getEnvironment().getLocation(u);
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			degrees = e.isControlDown() ? 90 : 15;
			turn(u, current, degrees);
			break;
		case KeyEvent.VK_LEFT:
			degrees = e.isControlDown() ? -90 : -15;
			turn(u, current, degrees);
			break;
		case KeyEvent.VK_UP:
			distance = e.isControlDown() ? 1 : 0.25;
			move(u, current, distance*Constants.INCHES_IN_PIXELS);
			break;
		case KeyEvent.VK_DOWN:
			distance = e.isControlDown() ? -1 : -0.25;
			move(u, current, distance*Constants.INCHES_IN_PIXELS);
			break;
		case KeyEvent.VK_F:
			if(e.isControlDown()){
				turn(u, current, 180);
			}
			break;
		}
	}


	protected void move(Unit u, Location current, double amount) {
		Location newLocation = GeometryUtils.calculateNewLocation(current, amount);
		Environment environment = display.getEnvironment();
		if(!environment.tooFar(u, current, newLocation)){
			environment.place(u, newLocation);
		}
	}

	protected void turn(Unit u, Location current, int degrees) {
		display.getEnvironment().place(u, new Location(current.x, current.y, current.direction + degrees));
	}

}
