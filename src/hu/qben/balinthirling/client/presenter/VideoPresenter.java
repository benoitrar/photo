package hu.qben.balinthirling.client.presenter;

import hu.qben.balinthirling.shared.JsString;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Kiss, Benedek
 *
 */
@SuppressWarnings("javadoc")
public class VideoPresenter implements Presenter {
	
	/**
	 * @author Kiss, Benedek
	 * 
	 * Defines the interface to handle display.
	 */
	public interface Display {
		void setUrl(String url);
		HTML getInfos();
		Widget asWidget();
	}

	private static final String JSON_URL = GWT.getHostPageBaseURL() + "php/video.php/?q=";
	
	private Display display;
	private final HandlerManager eventBus;
	
	public VideoPresenter(Display display, HandlerManager eventBus) {
		this.display = display;
		this.eventBus = eventBus;
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}
	
	public void setTheme(final String theme, final HasWidgets container) {
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL + theme);
		//Window.alert(JSON_URL + theme);
	    try {
	    	builder.sendRequest(null, new RequestCallback() {
				@Override
				public void onResponseReceived(Request request, Response response) {
					if(200 == response.getStatusCode()) {
						JsArray<JsString> data = asArrayOfBlocks(response.getText());
						if(data.length() == 2) {
							display.setUrl(data.get(0).getName());
							display.getInfos().setHTML(data.get(1).getName());//.replaceAll("___", "<br>"));
							go(container);
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
	
	private final native JsArray<JsString> asArrayOfBlocks(String json) /*-{
		return eval(json);
	}-*/;
	
}
