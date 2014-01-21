package hu.qben.balinthirling.shared;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Kiss, Benedek
 *
 */
public class JsFile extends JavaScriptObject {
	
	protected JsFile() {}
	
	/**
	 * @return directory's name
	 */
	public final native String getName() /*-{
		return this.name;
	}-*/;

	/**
	 * @return directory's type
	 */
	public final native String getType() /*-{
		return this.type;
	}-*/;
}