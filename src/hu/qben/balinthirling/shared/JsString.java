package hu.qben.balinthirling.shared;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Kiss, Benedek
 *
 */
public class JsString extends JavaScriptObject {
	
	protected JsString() {}
	
	/**
	 * @return directory's name
	 */
	public final native String getName() /*-{
		return this.name;
	}-*/;
}