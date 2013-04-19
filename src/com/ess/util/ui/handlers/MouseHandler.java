package com.ess.util.ui.handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;

import com.ess.util.mw.Constants;
import com.ess.util.mw.model.Unit;
import com.ess.util.mw.rw.Environment;
import com.ess.util.mw.rw.GeometryUtils;
import com.ess.util.mw.rw.Location;
import com.ess.util.ui.Display;

public class MouseHandler extends MouseAdapter {

	Logger log = Logger.getLogger(Display.class);
	Display display;

	public MouseHandler(Display display) {
		this.display = display;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (display.friendlySelected()) {
			Unit selected = display.getSelected();
			Location origin = display.getEnvironment().getLocation(selected);
			int angle = GeometryUtils.getAngle(origin, new Location(e.getX(), e.getY()));
			Location draggedLocation = new Location(e.getX(), e.getY(), 180 - angle);
			Unit collision = display.getEnvironment().getCollision(selected, draggedLocation);
			boolean tooFar = tooFar(selected, origin, draggedLocation);
			display.setCollision(collision != null || tooFar);
			display.setDraggedLocation(draggedLocation);
		}
	}

	protected boolean tooFar(Unit selected, Location origin, Location draggedLocation) {
		double distance = GeometryUtils.distance(draggedLocation, origin);
		boolean tooFar = distance / Constants.INCHES_IN_PIXELS > display.getEnvironment().getRemainingMove(selected);
		return tooFar;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clicked(display.getEnvironment(), e);
	}

	private void clicked(final Environment environment, MouseEvent e) {
		Location loc = new Location(e.getX(), e.getY());
		// log.debug("Clicked: "+loc);
		Unit u = environment.getUnitAt(loc);
		if (u != null) {
			int button = e.getButton();
			if (button == MouseEvent.BUTTON1) {
				log.debug(u);
				display.setSelected(u);
				display.getUnitBox().setSelectedUnit(u);
				System.out.println("Remaining move: " + environment.getRemainingMove(u));
			} else if (button == MouseEvent.BUTTON3) {
				// showPopup(loc);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Location draggedLocation = display.getDraggedLocation();
		if (draggedLocation != null) {
			Location origin = display.getEnvironment().getLocation(display.getSelected());
			boolean tooFar = tooFar(display.getSelected(), origin, draggedLocation);
			if (draggedLocation != null && !tooFar) {
				display.getEnvironment().place(display.getSelected(), draggedLocation);
			}
			display.setDraggedLocation(null);
		}
	}

}
