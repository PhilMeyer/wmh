package com.ess.util.mw;

import junit.framework.TestCase;

import com.ess.util.ag.ColAvoid;
import com.ess.util.ag.Environment;
import com.ess.util.ag.GeometryUtil;
import com.ess.util.mw.rw.Location;

public class TestCollision extends TestCase{


	Environment env = new Environment();
	ColAvoid col = new ColAvoid(env);
	AltUnit a = UnitFactory.forgeGuard("A");
	AltUnit z = UnitFactory.forgeGuard("B");
	AltUnit c = UnitFactory.forgeGuard("C");
	AltUnit d = UnitFactory.forgeGuard("D");
	AltUnit e = UnitFactory.forgeGuard("E");
	AltUnit f = UnitFactory.forgeGuard("F");
	AltUnit g = UnitFactory.forgeGuard("G");
	Location aLoc = new Location(0,0);
	Location tLoc = new Location(0,0);
	Location zLoc = new Location(100,100);
	Location cLoc = new Location(68,68);
	Location dLoc = new Location(81,59);
	Location eLoc = new Location(59,81);
	Location fLoc = new Location(96,55);
	Location gLoc = new Location(55,96);

	@Override		
	public void setUp(){
		env.place(a,aLoc);
		env.place(z,zLoc);
		//env.place(c,cLoc);
		//env.place(d,dLoc);
		//env.place(e,eLoc);
		//env.place(f,fLoc);
		//env.place(g,gLoc);
	}
	
	public void test2(){
		Location placement = null;
		do{
			AltUnit temp = UnitFactory.forgeGuard();
			env.place(temp, aLoc);
			placement = col.getWorkable(temp, z, 0);
			if(placement != null){
				env.place(temp, placement);
				System.out.println(placement);
			}
		} while (placement != null);
		System.out.println(env.unitLocations.keySet().size());
		do{
			AltUnit temp = UnitFactory.forgeGuard();
			env.place(temp, tLoc);
			placement = col.getWorkable(temp, z, 120);
			env.place(temp, placement);
			System.out.println(placement);
		} while (placement != null);
		System.out.println(env.unitLocations.keySet().size());
	}
	
	
	
	//public void test1(){
		//System.out.println(e.isCollision(UnitFactory.forgeGuard(), new Location(10,10)));
		//System.out.println(e.isCollision(UnitFactory.forgeGuard(), new Location(30,0)));
		//System.out.println(e.isCollision(UnitFactory.forgeGuard(), new Location(15,0)));
		//System.out.println(e.isCollision(UnitFactory.forgeGuard(), new Location(14,0)));
		//System.out.println(e.isCollision(UnitFactory.forgeGuard(), new Location(14,14)));
		//System.out.println(e.isCollision(UnitFactory.forgeGuard(), new Location(-10,10)));
	//}
}
