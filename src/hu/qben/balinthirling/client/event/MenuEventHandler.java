package hu.qben.balinthirling.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Kiss, Benedek
 *
 */
public interface MenuEventHandler extends EventHandler {

	/**
	 * @param menuEvent
	 */
	void onMenuEvent(MenuEvent menuEvent);
	
}
