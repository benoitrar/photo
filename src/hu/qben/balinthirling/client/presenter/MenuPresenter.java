package hu.qben.balinthirling.client.presenter;

import hu.qben.balinthirling.client.AppController;
import hu.qben.balinthirling.client.control.MenuCommand;
import hu.qben.balinthirling.client.event.MenuEvent;
import hu.qben.balinthirling.shared.JsFile;

import java.util.LinkedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Kiss, Benedek
 * 
 * This class defines the functionality and the <code>Display</code> interface for <code>MenuView</code>.
 */
@SuppressWarnings("javadoc")
public class MenuPresenter implements Presenter {
	
	/**
	 * @author Kiss, Benedek
	 * 
	 * Defines the interface to handle display.
	 */
	public interface Display {
		MenuBar getMenu();
		Widget asWidget();
	}

	private final HandlerManager eventBus;
	private final Display display;
	private static LinkedList<String> menuItems = new LinkedList<String>();
	
	private static final String JSON_URL = GWT.getHostPageBaseURL() + "php/photos.php";
	
	public static final String SLIDESHOWS = "slideshows";
	public static final String COMMISSIONED_WORK = "commissioned_work";
	public static final String BIO = "bio";
	public static final String CONTACT = "contact";
	public static final String WELCOME = "welcome";
	
	/**
	 * @param eventBus this will register events
	 * @param display this will added to container
	 */
	public MenuPresenter(HandlerManager eventBus, Display display) {
		this.eventBus = eventBus;
		this.display = display;
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		bind();
		container.add(display.asWidget());
	}
	
	private void bind() {
		String url = URL.encode(JSON_URL);
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

		try {
			builder.sendRequest(null, new RequestCallback() {
				@Override
				public void onResponseReceived(Request request,
						Response response) {
					if (200 == response.getStatusCode()) {
						//Window.alert(response.getText());
						listMenuItems(asArrayOfDirectoryNames(response.getText()));
					} else {
						Window.alert("Couldn't retrieve JSON (" + response.getStatusText()
								+ ")");
					}
				}
				@Override
				public void onError(Request request, Throwable exception) {
					Window.alert("Couldn't retrieve JSON");
				}
			});
		} catch (RequestException e) {
			Window.alert("Couldn't retrieve JSON");
		}
		
		display.getMenu().addHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(event.getNativeKeyCode() == KeyCodes.KEY_LEFT) {
					FramePresenter.prevImage();
				} else if(event.getNativeKeyCode() == KeyCodes.KEY_RIGHT) {
					FramePresenter.nextImage();
				}
			}
		}, KeyDownEvent.getType());

	}

	/**
	 * @param arr
	 */
	private void listMenuItems(JsArray<JsFile> arr) {
		for(int i=0;i<arr.length();i++) {
			addMenuItem(arr.get(i).getName(), arr.get(i).getType());
		}
		//TODO
		//addMenuItem(SLIDESHOWS);
		
		addMenuSeparator();
		
		//TODO remove commissioned work altogether
		//addMenuItem(COMMISSIONED_WORK, AppController.STATE_COMMISSIONED);
		addMenuItem(BIO, AppController.STATE_TEXT);
		addMenuItem(CONTACT, AppController.STATE_TEXT);
	}

	private void addMenuItem(String name, String type) {
		//String programmingName = name.replace(' ', '_');
		menuItems.add(name);
		display.getMenu().addItem(new MenuItem(name.toUpperCase().replace('_', ' '), new MenuCommand(new MenuEvent(name.replace(' ', '_'), type), eventBus)));
	}
	
	private void addMenuSeparator() {
		for(int i=0;i<10;i++) {
			display.getMenu().addSeparator();
		}
	}
	
	private final native JsArray<JsFile> asArrayOfDirectoryNames(String json) /*-{
		return eval(json);
	}-*/;

	public static LinkedList<String> getMenuItems() {
		return menuItems;
	}
	
}
