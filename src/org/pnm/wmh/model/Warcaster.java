package org.pnm.wmh.model;


public class Warcaster extends Unit{

	public final int foc;
	
	public Warcaster(String name, String imagePath, Base base, int spd, int str, int mat, int rat, int def, int arm,
			int cmd, int foc) {
		super(name, imagePath, base, spd, str, mat, rat, def, arm, cmd);
		this.foc = foc;
	}

}
