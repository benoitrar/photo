package hu.qben.balinthirling.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;

/**
 * @author Kiss, Benedek
 *
 */
public class BalintHirling implements EntryPoint {

	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	@Override
	public void onModuleLoad() {
	    HandlerManager eventBus = new HandlerManager(null);
	    AppController appViewer = new AppController(eventBus);
	    appViewer.go();
	}
	
}
