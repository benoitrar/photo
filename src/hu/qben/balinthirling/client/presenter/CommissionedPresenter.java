/**
 * 
 */
package hu.qben.balinthirling.client.presenter;

import hu.qben.balinthirling.client.AppController;
import hu.qben.balinthirling.client.control.MenuCommand;
import hu.qben.balinthirling.client.event.MenuEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Kiss, Benedek
 *
 */
@SuppressWarnings("javadoc")
public class CommissionedPresenter implements Presenter {
	
	/**
	 * @author Kiss, Benedek
	 * 
	 * Defines the interface to handle display.
	 */
	public interface Display {
		Image getWedding();
		Image getFitness();
		Image getArchitecture();
		Widget asWidget();
	}
	
	public final static String WEDDING = "wedding";
	public final static String FITNESS = "fitness";
	public final static String ARCHITECTURE = "architecture";
	
	private static Display display;
	private static HandlerManager eventBus;
	
	public CommissionedPresenter(Display display, HandlerManager eventBus) {
		CommissionedPresenter.display = display;
		CommissionedPresenter.eventBus = eventBus;
		
		bind();
	}
	
	private void bind() {
		display.getWedding().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				new MenuCommand(new MenuEvent(WEDDING, AppController.STATE_COMMISSIONED), eventBus).execute();
			}
		});
		display.getFitness().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				new MenuCommand(new MenuEvent(FITNESS, AppController.STATE_COMMISSIONED), eventBus).execute();
			}
		});
		display.getArchitecture().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				new MenuCommand(new MenuEvent(ARCHITECTURE, AppController.STATE_COMMISSIONED), eventBus).execute();
			}
		});
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}
	
}
