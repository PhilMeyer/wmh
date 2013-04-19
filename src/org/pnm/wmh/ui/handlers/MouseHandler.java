package org.pnm.wmh.ui.handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;
import org.pnm.wmh.Constants;
import org.pnm.wmh.model.Unit;
import org.pnm.wmh.rw.Environment;
import org.pnm.wmh.rw.GeometryUtils;
import org.pnm.wmh.rw.Location;
import org.pnm.wmh.ui.Display;


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
			Environment environment = display.getEnvironment();
			Location origin = environment.getLocation(selected);
			int angle = GeometryUtils.getAngle(origin, new Location(e.getX(), e.getY()));
			Location draggedLocation = new Location(e.getX(), e.getY(), 180 - angle);
			Unit collision = environment.getCollision(selected, draggedLocation);
			boolean tooFar = environment.tooFar(selected, origin, draggedLocation);
			display.setIllegalMove(collision != null || tooFar);
			display.setDraggedLocation(draggedLocation);
		}
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
		display.getFrame().requestFocus();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Location draggedLocation = display.getDraggedLocation();
		if (draggedLocation != null) {
			Environment environment = display.getEnvironment();
			Location origin = environment.getLocation(display.getSelected());
			boolean tooFar = environment.tooFar(display.getSelected(), origin, draggedLocation);
			if (draggedLocation != null && !tooFar) {
				environment.place(display.getSelected(), draggedLocation);
			}
			display.setDraggedLocation(null);
		}
	}

}
