package hu.qben.balinthirling.shared;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/**
 * @author Kiss, Benedek
 *
 */
@SuppressWarnings("javadoc")
public class JsFolders extends JavaScriptObject {
	
	protected JsFolders() {}
	
	public final native JsArrayString getImageFolders() /*-{
		return this[0].I;
	}-*/;

	public final native JsArrayString getVideoFolders() /*-{
		return this[0].V;
	}-*/;
}