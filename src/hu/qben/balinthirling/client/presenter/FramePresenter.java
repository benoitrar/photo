package hu.qben.balinthirling.client.presenter;

import hu.qben.balinthirling.shared.JsImage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Kiss, Benedek
 *
 */
@SuppressWarnings("javadoc")
public class FramePresenter implements Presenter {
	
	private static final String NAVIGATION_MESSAGE = "";
	private static final int AUTO_STEP_IN_MILLIS = 3000;
	private static final Timer autoStepTimer = createAutoStepTimer(AUTO_STEP_IN_MILLIS);
	
	/**
	 * @author Kiss, Benedek
	 * 
	 * Defines the interface to handle display.
	 */
	public interface Display {
		Image getImage();
		Button getLeftStepper();
		HTML getImageNumberLabel();
		Button getRightStepper();
		HTML getCaptionLabel();
		Widget asWidget();
	}
	
	
	private static Display display;
	private HasWidgets container;
	private final HandlerManager eventBus;
	
	public static final String JSON_URL = GWT.getHostPageBaseURL() + "php/info.php?q=";
	public static final String PHOTOS = "photos";
	public static final String COMMISSIONED = "commissioned_work";
	
	private static final String TRANSPARENT = GWT.getHostPageBaseURL() + "img/transparent.jpg";
	private static final String WHITE = GWT.getHostPageBaseURL() + "img/white.png";
	
	private static int counter;
	private static JsArray<JsImage> images;
	private static String folderName;
	private static String themeName;

	/**
	 * @param display this will be added to container
	 * @param labeldiv this label will contain the buttons to switch between images
	 */
	public FramePresenter(Display display, HandlerManager eventBus) {
		FramePresenter.display = display;
		this.eventBus = eventBus;
		//FramePresenter.folderName = PHOTOS;
		//FramePresenter.themeName = MenuPresenter.WELCOME;
		
		bind();
		//init();
	}

	private void init() {
		display.getImage().setUrl(WHITE);
		display.getImageNumberLabel().setText("");
		display.getCaptionLabel().setText("");
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL + folderName + "/" + themeName);
		//Window.alert(JSON_URL + folderName + "/" + themeName);
	    try {
	    	builder.sendRequest(null, new RequestCallback() {
				@Override
				public void onResponseReceived(Request request, Response response) {
					if(200 == response.getStatusCode()) {
						images = asArrayOfFileNames(response.getText());
						if(images.length() == 0) {
							display.getImageNumberLabel().setText("There are no images in this category.");
						} else {
							counter = 0;
							display.getImage().setUrl(getUrl());
							setSteppers();
							setLabels();
						}
						//go(container);

						container.clear();
						container.add(display.asWidget());
					} else {
						//Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
					}
				}
				@Override
				public void onError(Request request, Throwable exception) {
					//Window.alert("Couldn't retrieve JSON");
				}
	    	});
	    } catch (RequestException e) {
	    	//Window.alert("Couldn't retrieve JSON");
	    }
	    
	}

	protected static String getUrl() {
		return GWT.getHostPageBaseURL() + "content/" + folderName + "/" + themeName + "/" + images.get(counter).getName();
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		this.container = container;
		init();
	}

	private void bind() {
		display.getImage().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				nextImage();
			}
		});
		
		display.getImage().addHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(event.getNativeKeyCode() == KeyCodes.KEY_LEFT) {
					FramePresenter.prevImage();
				} else if(event.getNativeKeyCode() == KeyCodes.KEY_RIGHT) {
					FramePresenter.nextImage();
				}
			}
		}, KeyDownEvent.getType());
		
		display.getLeftStepper().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				FramePresenter.prevImage();
			}
		});
		
		display.getRightStepper().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				FramePresenter.nextImage();
			}
		});
	}
	
	/**
	 * 
	 */
	public static void nextImage() {
		if(counter < images.length()-1) {
			counter++;
		} else {
			counter = 0;
		}
		display.getImage().setUrl(getUrl());
		setLabels();
	}
	
	/**
	 * 
	 */
	public static void prevImage() {
		if(counter > 0) {
			counter--;
		} else {
			counter = images.length()-1;
		}
		display.getImage().setUrl(getUrl());
		setLabels();
	}
	
	private static void setLabels() {
		if(!themeName.equals(MenuPresenter.WELCOME)) {
			if(counter == 0) {
				display.getImageNumberLabel().setHTML("");
				display.getCaptionLabel().setHTML(NAVIGATION_MESSAGE);
			} else {
				display.getImageNumberLabel().setHTML(counter + "/" + (images.length()-1));
				display.getCaptionLabel().setHTML(images.get(counter).getCaption());
			}
		}
	}
	
	private void setSteppers() {
		if(themeName.equals(MenuPresenter.WELCOME)) {
			setSteppersVisibility(false);
		} else {
			setSteppersVisibility(true);
		}
	}
	
	/**
	 * @param visibility
	 */
	private void setSteppersVisibility(boolean visibility) {
		display.getLeftStepper().setVisible(visibility);
		display.getRightStepper().setVisible(visibility);
	}

	private final native JsArray<JsImage> asArrayOfFileNames(String json) /*-{
		return eval(json);
	}-*/;

	public void setTheme(String folderName, String themeName) {
		FramePresenter.folderName = folderName;
		FramePresenter.themeName = themeName;
	}

	/**
	 * @param autoStepInMillis 
	 * @return 
	 * 
	 */
	private static Timer createAutoStepTimer(int autoStepInMillis) {
		Timer timer = new Timer() {
			@Override public void run() {
				if(isAutoSteppingEnabled()) {
					nextImage();
				}
			}
		};
		timer.scheduleRepeating(autoStepInMillis);
		return timer;
	}
	
	private static boolean isAutoSteppingEnabled() {
		return themeName.equals(MenuPresenter.WELCOME);
	}
}
