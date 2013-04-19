package com.ess.util.ui;

public class DisplayRunner implements Runnable{

	private GameFrame frame;

	public DisplayRunner(GameFrame frame){
		this.frame = frame;
	}
	
	public void run(){
		for(;;){
			frame.refreshDisplay();
			sleep();
		}
	}

	private static void sleep() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
