package hu.qben.balinthirling.client;

import hu.qben.balinthirling.client.event.MenuEvent;
import hu.qben.balinthirling.client.event.MenuEventHandler;
import hu.qben.balinthirling.client.presenter.CommissionedPresenter;
import hu.qben.balinthirling.client.presenter.FramePresenter;
import hu.qben.balinthirling.client.presenter.MenuPresenter;
import hu.qben.balinthirling.client.presenter.TextInfoPresenter;
import hu.qben.balinthirling.client.presenter.VideoPresenter;
import hu.qben.balinthirling.client.view.CommissionedView;
import hu.qben.balinthirling.client.view.FrameView;
import hu.qben.balinthirling.client.view.MenuView;
import hu.qben.balinthirling.client.view.TextInfoView;
import hu.qben.balinthirling.client.view.VideoView;
import hu.qben.balinthirling.shared.JsString;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author Kiss, Benedek
 *
 */
@SuppressWarnings("javadoc")
public class AppController implements ValueChangeHandler<String> {
	private static HandlerManager eventBus;
	private static String state;
	private static String subState;
	private static boolean first = true;
	
	public static final String STATE_IMAGE = "I";
	public static final String STATE_TEXT = "T";
	public static final String STATE_COMMISSIONED = "C";
	public static final String STATE_VIDEO = "V";
	
	private static final FlexTable skeleton = new FlexTable();
	private static final HTMLPanel mainDiv = new HTMLPanel("<div id='main'></div>");
	private static final HTMLPanel titleDiv = new HTMLPanel("<div id='title'><a href='#'><img src='img/hirling_balint_felirat_kicsi.jpg' /></a></div>");	//new HTMLPanel("<div id='title'><a href='#'><b>BALINT HIRLING</b> | PHOTOGRAPHY</a></div>");
	private static final FlexTable contentTable = new FlexTable();
	private static final HTMLPanel menuDiv = new HTMLPanel("<div id='menu'></div>");
	private static final HTMLPanel frameDiv = new HTMLPanel("<div id='frame'></div>");
	private static final String JSON_URL = GWT.getHostPageBaseURL() + "php/type.php/?q=";
	
	private static MenuPresenter menuPresenter;
	private static FramePresenter framePresenter;
	private static CommissionedPresenter commissionedPresenter;
	private static TextInfoPresenter textInfoPresenter;
	private static VideoPresenter videoPresenter;
	
	/**
	 * @param eventBus this will register events
	 */
	public AppController(HandlerManager eventBus) {
		AppController.eventBus = eventBus;
		createSkeleton();
		bind();
	}

	private void createSkeleton() {
		mainDiv.addStyleName("main");
		titleDiv.setStyleName("title");
		contentTable.addStyleName("content");
		menuDiv.addStyleName("menu");
		frameDiv.addStyleName("frame");
		
		skeleton.setWidget(0, 0, titleDiv);
		skeleton.setWidget(1, 0, contentTable);
		
		contentTable.setWidget(0, 0, menuDiv);
		contentTable.setWidget(0, 1, frameDiv);
		
		mainDiv.add(skeleton);
		RootPanel.get("app").add(mainDiv);
	}

	private void bind() {
		History.addValueChangeHandler(this);
		
		RootPanel.get().addDomHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(event.getNativeKeyCode() == KeyCodes.KEY_LEFT) {
					FramePresenter.prevImage();
				} else if(event.getNativeKeyCode() == KeyCodes.KEY_RIGHT) {
					FramePresenter.nextImage();
				}
			}
		}, KeyDownEvent.getType());
		
		eventBus.addHandler(MenuEvent.TYPE, new MenuEventHandler() {
			@Override
			public void onMenuEvent(MenuEvent menuEvent) {
				setState(menuEvent.type);
				History.newItem(menuEvent.name);
			}
		});
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		//Window.alert((state==null?"state null":"state "+state) + ", " + (token==null?"token null":"token "+token));
		
		if(token != null) {
			subState = token;
			
			if(first) {
				menuPresenter = new MenuPresenter(eventBus, new MenuView());
				menuPresenter.go(menuDiv);
				framePresenter = new FramePresenter(new FrameView(), eventBus);
				
				first = false;
			}
			
			if(token.equals("")) {
				state = STATE_IMAGE;
				framePresenter.setTheme(FramePresenter.PHOTOS, MenuPresenter.WELCOME);
				framePresenter.go(frameDiv);
			} else if(token.equals(MenuPresenter.COMMISSIONED_WORK)) {
				state = STATE_COMMISSIONED;
				getCommissionedPresenter().go(frameDiv);
			} else if(token.equals(CommissionedPresenter.WEDDING)
					|| token.equals(CommissionedPresenter.FITNESS)
					|| token.equals(CommissionedPresenter.ARCHITECTURE)) {
				state = STATE_COMMISSIONED;
				framePresenter.setTheme(FramePresenter.COMMISSIONED, token);
				framePresenter.go(frameDiv);
			} else if(token.equals(MenuPresenter.BIO) || token.equals(MenuPresenter.CONTACT)) {
				state = STATE_TEXT;
				getTextInfoPresenter(token).go(frameDiv);
			} else {
				checkType(token);
			}
		}
	}

	/**
	 * @param token
	 * @return
	 */
	private void checkType(final String token) {		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL + token);
		//Window.alert(JSON_URL + theme);
	    try {
	    	builder.sendRequest(null, new RequestCallback() {
				@Override
				public void onResponseReceived(Request request, Response response) {
					if(200 == response.getStatusCode()) {
						JsArray<JsString> data = asStringArray(response.getText());
						AppController.setState(data.get(0).getName());
						AppController.setSubState(token);
						
						if(state.equals(STATE_VIDEO)) {
							getVideoPresenter().setTheme(token, frameDiv);
						} else if(state.equals(STATE_IMAGE)) {
							framePresenter.setTheme(FramePresenter.PHOTOS, token);
							framePresenter.go(frameDiv);
						}
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

	public void go() {
		History.fireCurrentHistoryState();
	}

	private static void setState(String state) {
		AppController.state = state;
	}

	private static void setSubState(String subState) {
		AppController.subState = subState;
	}

	private static CommissionedPresenter getCommissionedPresenter() {
		if(commissionedPresenter == null) {
			commissionedPresenter = new CommissionedPresenter(new CommissionedView(), eventBus);
		}
		return commissionedPresenter;
	}

	private static TextInfoPresenter getTextInfoPresenter(String token) {
		if(textInfoPresenter == null) {
			textInfoPresenter = new TextInfoPresenter(new TextInfoView());
		}
		textInfoPresenter.setSource(token);
		return textInfoPresenter;
	}

	private static VideoPresenter getVideoPresenter() {
		if(videoPresenter == null) {
			videoPresenter = new VideoPresenter(new VideoView(), eventBus);
		}
		return videoPresenter;
	}
	
	private final native JsArray<JsString> asStringArray(String json) /*-{
		return eval(json);
	}-*/;
	
}
