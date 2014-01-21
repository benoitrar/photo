/**
 * 
 */
package hu.qben.balinthirling.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Kiss, Benedek
 * 
 * The base interface for every presenter.
 * They must implement "go" method for rendering the UI.
 */
public interface Presenter {
	/**
	 * @param container on which the display will be rendered.
	 */
	public abstract void go(final HasWidgets container);
}
