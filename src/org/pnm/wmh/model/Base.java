package org.pnm.wmh.model;

import org.pnm.wmh.Constants;

public enum Base {

	SMALL(30), MEDIUM(40), LARGE(50);
	
	public final int size;
	
	private Base(int size){
		this.size = size;
	}
	
	public double scaled(){
		return size * Constants.SCALE_FACTOR;
	}
}
