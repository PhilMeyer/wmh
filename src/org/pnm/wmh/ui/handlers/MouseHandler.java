package org.pnm.wmh.ui.handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;
import org.pnm.wmh.event.ActivationEvent;
import org.pnm.wmh.event.EventManager;
import org.pnm.wmh.event.SelectionEvent;
import org.pnm.wmh.model.Unit;
import org.pnm.wmh.rw.Environment;
import org.pnm.wmh.rw.Game;
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
		if (display.activatedSelected()) {
			Unit unit = display.getGame().getActive();
			if (unit != null) {
				Environment environment = display.getEnvironment();
				Location origin = environment.getLocation(unit);
				int angle = GeometryUtils.getAngle(origin, new Location(e.getX(), e.getY()));
				Location draggedLocation = new Location(e.getX(), e.getY(), 180 - angle);
				Unit collision = environment.getCollision(unit, draggedLocation);
				boolean tooFar = environment.tooFar(unit, origin, draggedLocation);
				display.setIllegalMove(collision != null || tooFar);
				display.setDraggedLocation(draggedLocation);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clicked(display.getGame(), e);
	}

	private void clicked(final Game game, MouseEvent e) {
		Location loc = new Location(e.getX(), e.getY());
		// log.debug("Clicked: "+loc);
		Environment environment = game.getEnvironment();
		Unit u = environment.getUnitAt(loc);
		if (u != null) {
			int button = e.getButton();
			if (button == MouseEvent.BUTTON1) {
				int clicks = e.getClickCount();
				if (clicks > 1 && game.canActivate(u)) {
					EventManager.notify(new ActivationEvent(u));
				}
				log.debug(u);
				EventManager.notify(new SelectionEvent(u));
				System.out.println("Remaining move: " + game.getRemainingMove(u));
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
