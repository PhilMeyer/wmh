package org.pnm.wmh.ui;

import org.pnm.wmh.model.Unit;
import org.pnm.wmh.rw.Location;


public class UserState {

	Unit selected;
	Location dragged;
	//Unit activated;
	boolean illegalMove;

	public Unit getSelected() {
		return selected;
	}

	public void setSelected(Unit selected) {
		this.selected = selected;
	}

	public Location getDragged() {
		return dragged;
	}

	public void setDragged(Location dragged) {
		this.dragged = dragged;
	}

//	public Unit getActivated() {
//		return activated;
//	}
//
//	public void setActivated(Unit activated) {
//		this.activated = activated;
//	}

	public boolean isIllegalMove() {
		return illegalMove;
	}

	public void setIllegalMove(boolean illegalMove) {
		this.illegalMove = illegalMove;
	}

}
