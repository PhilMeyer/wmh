package com.ess.util.ui.handlers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.ess.util.mw.Constants;
import com.ess.util.mw.model.Unit;
import com.ess.util.mw.rw.GeometryUtils;
import com.ess.util.mw.rw.Location;
import com.ess.util.ui.Display;

public class KeyHandler extends KeyAdapter {

	private Display display;

	public KeyHandler(Display display) {
		this.display = display;
	}

	@Override
	public void keyPressed(KeyEvent e) {
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
		display.getEnvironment().place(u, newLocation);
	}

	protected void turn(Unit u, Location current, int degrees) {
		display.getEnvironment().place(u, new Location(current.x, current.y, current.direction + degrees));
	}

}
