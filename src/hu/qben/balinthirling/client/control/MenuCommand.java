package hu.qben.balinthirling.client.control;

import hu.qben.balinthirling.client.event.MenuEvent;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.HandlerManager;

/**
 * @author Kiss, Benedek
 * This class is contains the necessary data for handling a click on a menu item.
 */
public final class MenuCommand implements ScheduledCommand {
	
	private final MenuEvent event;
	private final HandlerManager eventBus;
	
	/**
	 * @param name the menu item's name
	 * @param eventBus that will handle events of this commander
	 */
	public MenuCommand(MenuEvent event, HandlerManager eventBus) {
		this.event = event;
		this.eventBus = eventBus;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.Scheduler.ScheduledCommand#execute()
	 */
	@Override
	public void execute() {
		eventBus.fireEvent(event);
	}
	
}
