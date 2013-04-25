package org.pnm.wmh.event;

import org.pnm.wmh.model.Unit;

public class SelectionEvent {

	public final Unit u;
	
	public SelectionEvent(Unit u){
		this.u = u;
	}
}
