package org.pnm.wmh.event;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

	
	static List<ActivationListener> activationListeners = new ArrayList<>();
	static List<SelectionListener> selectionListeners = new ArrayList<>();
	
	public static void notify(ActivationEvent e){
		for(ActivationListener listener : activationListeners){
			listener.handle(e);
		}
	}
	
	public static void notify(SelectionEvent e){
		for(SelectionListener listener : selectionListeners){
			listener.handle(e);
		}
	}
	
	public static void registerActivationListener(ActivationListener listener){
		activationListeners.add(listener);
	}
	
	public static void registerSelectionListener(SelectionListener listener){
		selectionListeners.add(listener);
	}
	
}
