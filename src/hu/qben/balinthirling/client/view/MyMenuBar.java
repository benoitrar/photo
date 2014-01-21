package hu.qben.balinthirling.client.view;

import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

/**
 * @author Kiss, Benedek
 *
 */
public class MyMenuBar extends MenuBar {
	
	/**
	 * @param vertical
	 */
	public MyMenuBar(boolean vertical) {
		super(vertical);
	}
	
	public MenuItem getSelectedItem() {
		return super.getSelectedItem();
	}
}
