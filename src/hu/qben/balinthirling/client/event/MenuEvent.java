package hu.qben.balinthirling.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Kiss, Benedek
 *
 */
public class MenuEvent extends GwtEvent<MenuEventHandler> {
	/**
	 * Returned when asking for handler for this type.
	 */
	public final static Type<MenuEventHandler> TYPE = new Type<MenuEventHandler>();
	/**
	 * By this name can event be handled.
	 */
	public final String name;
	/**
	 * Type of the menu item selected.
	 */
	public final String type;
	
	/**
	 * @param name the triggering menu item's name
	 */
	public MenuEvent(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	@Override
	public Type<MenuEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(MenuEventHandler handler) {
		handler.onMenuEvent(this);
	}
}
