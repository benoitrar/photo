package hu.qben.balinthirling.client.view;

import hu.qben.balinthirling.client.presenter.MenuPresenter;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Kiss, Benedek
 * 
 * This class defines the structure of the menu view.
 * Basically, it has only one member: a <code>MenuBar</code>.
 */
public class MenuView extends Composite implements MenuPresenter.Display {
	
	/**
	 * The main menu. <code>MenuItems</code> will be added by <code>MenuPresenter</code>.
	 */
	private static final MenuBar menu = new MenuBar(true);
	private static final FlexTable content = new FlexTable();
    
	/**
	 * Adds a menu without options and functionality.
	 */
	public MenuView() {
		initWidget(content);
		VerticalPanel panel = new VerticalPanel();
		panel.add(menu);
		content.setWidget(0, 0, panel);
		content.addStyleName("menu-content");
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.WelcomePresenter.Display#getMenuBar()
	 */
	@Override
	public MenuBar getMenu() {
		return menu;
	}

}
