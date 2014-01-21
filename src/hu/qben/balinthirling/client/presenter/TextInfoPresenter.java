/**
 * 
 */
package hu.qben.balinthirling.client.presenter;

import hu.qben.balinthirling.shared.JsString;

import java.util.LinkedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Kiss, Benedek
 *
 */
@SuppressWarnings("javadoc")
public class TextInfoPresenter implements Presenter {
	
	public interface Display {
		void setData(JsArray<JsString> jsArray);
		Widget asWidget();
	}
	
	private Display display;
	
	private static LinkedList<String> infoList;
	private static String source;
	
	private static final String CONTACT_URL = GWT.getHostPageBaseURL() + "php/contact.php";
	private static final String BIO_URL = GWT.getHostPageBaseURL() + "php/bio.php";
	
	public TextInfoPresenter(Display display) {
		this.display = display;
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	@Override
	public void go(final HasWidgets container) {
		
		RequestBuilder builder = null;
		if(source.equals(MenuPresenter.BIO)) {
			builder = new RequestBuilder(RequestBuilder.GET, BIO_URL);
		} else if(source.equals(MenuPresenter.CONTACT)) {
			builder = new RequestBuilder(RequestBuilder.GET, CONTACT_URL);
		}
		//Window.alert(JSON_URL);
	    try {
	    	builder.sendRequest(null, new RequestCallback() {
				@Override
				public void onResponseReceived(Request request, Response response) {
					if(200 == response.getStatusCode()) {
						//Window.alert(response.getText());
						display.setData(asArrayOfLines(response.getText()));
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
	
	private final native JsArray<JsString> asArrayOfLines(String json) /*-{
		return eval(json);
	}-*/;

	public static String getSource() {
		return source;
	}

	public void setSource(String source) {
		TextInfoPresenter.source = source;
	}
	
}
