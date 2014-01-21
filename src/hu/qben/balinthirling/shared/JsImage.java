package hu.qben.balinthirling.shared;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Kiss, Benedek
 *
 */
public class JsImage extends JavaScriptObject {
	
	protected JsImage() {}
	
	/**
	 * @return image's name
	 */
	public final native String getName() /*-{
		return this.name;
	}-*/;

	/**
	 * @return image's caption
	 */
	public final native String getCaption() /*-{
		return this.caption;
	}-*/;
}